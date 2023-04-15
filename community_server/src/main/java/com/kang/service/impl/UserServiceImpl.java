package com.kang.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.kang.domain.Mail;
import com.kang.entity.Coin;
import com.kang.entity.Favorite;
import com.kang.entity.Record;
import com.kang.entity.User;
import com.kang.entity.vo.LoginVo;
import com.kang.entity.vo.PassVo;
import com.kang.entity.vo.PayVo;
import com.kang.entity.vo.SomeNum;
import com.kang.mapper.CoinMapper;
import com.kang.mapper.FavoriteMapper;
import com.kang.mapper.RecordMapper;
import com.kang.mapper.UserMapper;
import com.kang.service.UserService;
import com.kang.domain.Result;
import com.kang.utils.JwtUtil;
import com.kang.utils.MailUtil;
import com.kang.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CoinMapper coinMapper;

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private RecordMapper recordMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MailUtil mailUtil;

    @Override
    public User selectUserByParam(User user) {
        return userMapper.selectUserByParam(user);
    }

    /**
     * 生成四位验证码和图片
     */
    @Override
    public Map<Object, Object> getImgCode() {
        String key = UUID.randomUUID().toString();
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(200, 80);
        shearCaptcha.setGenerator(randomGenerator);
        String code = shearCaptcha.getCode();
        byte[] imageBytes = shearCaptcha.getImageBytes();
        /*
          原生转码前面没有 data:image/jpg;base64 这些字段，返回给前端是无法被解析，可以让前端加，也可以在下面加上
         */
        String base64Img = "data:image/jpg;base64," + Base64.encode(imageBytes);
        //  方便测试 code = "1234";
        redisUtil.set("captcha:" + key, code, 60);
        return MapUtil.builder().put("key", key).put("captchaImg", base64Img).build();
    }

    /**
     * 发送验证码到邮件
     * @param email 用户邮箱
     * @return
     */
    @Override
    public Map<Object, Object> getEmailCode(String email) {
        Mail mail = new Mail();
        mail.setSubject("登录验证码(请勿回复此消息)");
        mail.setRecipient(email);
        String key = UUID.randomUUID().toString();
        String code = RandomUtil.randomNumbers(6);
        String content = "您正在进行登录操作 , 验证码为：" + code + " , 2分钟内有效 , 请勿告知他人 , 如非本人操作可忽略此条消息 ！";
        mail.setContent(content);
        boolean success = mailUtil.sendEmail(mail);
        if (success) {
            redisUtil.set("captcha:" + key, code, 120);
            return MapUtil.builder().put("key", key).build();
        }
        return null;
    }

    @Override
    public SomeNum getSomeNum(Long id) {
        return userMapper.selectSomeNumById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)     // 支付流程事务 ，遇到异常就回滚
    public int goPay(PayVo payVo) {
        //  校验支付密码
        String md5Password = SecureUtil.md5(payVo.getPayPassword());
        User user=userMapper.selectByPrimaryKey(payVo.getPayUid());
        Coin userCoinInfo=coinMapper.selectByOwner(payVo.getPayUid());
        Coin authorCoinInfo = coinMapper.selectByOwner(payVo.getAuthorId());

        if(userCoinInfo.getCoinNum()<payVo.getGoodsCoin()){
            // 0 代表余额不足
            return 0;
        }

        if( null != user && md5Password.equals(user.getPayPassword())){
            //  密码正确
            // 修改用户硬币数
            userCoinInfo.setCoinNum(userCoinInfo.getCoinNum()-payVo.getGoodsCoin());
            userCoinInfo.setCoinUpdatetime(new Date());
            authorCoinInfo.setCoinNum(authorCoinInfo.getCoinNum()+payVo.getGoodsCoin());
            authorCoinInfo.setCoinUpdatetime(new Date());
            int i=coinMapper.updateByPrimaryKey(userCoinInfo);
            int j=coinMapper.updateByPrimaryKey(authorCoinInfo);


            Record record=new Record();
            if(j==1&&i==1){
                // 消费记录生成
                record.setRecordCreatetime(new Date());
                record.setWorksType(payVo.getGoodsType());
                record.setWorksId(payVo.getGoodsId());
                // 添加卖方记录
                record.setRecordId(UUID.randomUUID().toString());
                if(payVo.getGoodsType()==0){
                    record.setRecordDescribe("来自我发布的视频 《 "+payVo.getGoodsName()+" 》 的创作收益");
                } else if (payVo.getGoodsType()==1) {
                    record.setRecordDescribe("来自我发布的文章 《 "+payVo.getGoodsName()+" 》 的创作收益");
                } else if (payVo.getGoodsType()==2) {
                    record.setRecordDescribe("来自我回答的问题 《 "+payVo.getGoodsName()+" 》 的赏金收益");
                } else if (payVo.getGoodsType()==3) {
                    record.setRecordDescribe("来自我上次的文件 《 "+payVo.getGoodsName()+" 》 的创作收益");
                }
                record.setCoinChange("+"+payVo.getGoodsCoin());
                record.setAuthorId(authorCoinInfo.getCoinOwner());
                j=recordMapper.insertSelective(record);

                // 添加买方记录
                record.setRecordId(UUID.randomUUID().toString());
                if(payVo.getGoodsType()==0){
                    record.setRecordDescribe("购买了视频 《 "+payVo.getGoodsName()+" 》 ");
                } else if (payVo.getGoodsType()==1) {
                    record.setRecordDescribe("购买了文章 《 "+payVo.getGoodsName()+" 》 ");
                } else if (payVo.getGoodsType()==2) {
                    record.setRecordDescribe("支付了问题 《 "+payVo.getGoodsName()+" 》 的赏金");
                } else if (payVo.getGoodsType()==3) {
                    record.setRecordDescribe("购买了资源 《 "+payVo.getGoodsName()+" 》 ");
                }
                record.setCoinChange("-"+payVo.getGoodsCoin());
                record.setAuthorId(userCoinInfo.getCoinOwner());
                i=recordMapper.insertSelective(record);

            }
            return i==1&&j==1 ? 1 : -2 ;
        }else {
            // -1 代表密码错误
            return -1;
        }

    }

    @Override
    public int saveUser(User user) {
        if(StrUtil.isEmpty(user.getUserSign())){
            user.setUserSign("这个家伙很懒，什么都没有留下！");
        }
        user.setUpdateTime(new Date());
        // TODO 修改涉及到用户数据的其他表
        //设计表的时候偷了个懒，把用户头像、昵称存到了作品、评论里，导致用户修改的时候其他表中的用户信息改不了，算是个教训，该连表查还是得连表查
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int change(PassVo passVo) {
        User tempUser=userMapper.selectByPrimaryKey(passVo.getUid());
        User user=new User();
        user.setUserId(passVo.getUid());
        if(passVo.getType()==0 || passVo.getType()==1){
            // 登录密码
            if(passVo.getType()==1 && !tempUser.getUserPassword().equals(SecureUtil.md5(passVo.getOldPassword()))){
                return 0;
            }
            user.setUserPassword(SecureUtil.md5(passVo.getNewPassword()));
        }else {
            // 支付密码
            if(passVo.getType()==3 && !tempUser.getPayPassword().equals(SecureUtil.md5(passVo.getOldPassword()))){
                return 0;
            }
            user.setPayPassword(SecureUtil.md5(passVo.getNewPassword()));
        }
        userMapper.updateByPrimaryKeySelective(user);
        return 1;
    }

    @Override
    public Double getBalance(Long id) {
        return coinMapper.selectByOwner(id).getCoinNum();
    }


    @Override
    public Result login(LoginVo loginVo) {
        // 验证码
        String key="captcha:"+loginVo.getKey();
        String code= loginVo.getCode();
        if( StrUtil.isEmpty(code) || !redisUtil.hasKey(key) || !code.equals(redisUtil.get(key))){
            return Result.error(4000,"验证码错误");
        }

        String userEmail = loginVo.getUserEmail();
        if(StrUtil.isNotEmpty(userEmail)){
            // 判断出是邮箱登录
            // 然后判断用户是用邮箱登录还是注册
            User user = new User();
            user.setUserEmail(userEmail);
            user= selectUserByParam(user);
            if(user!=null){
                // 登录
                return goLogin(loginVo,0);
            }else {
                // 注册
                return goRegister(loginVo);
            }
        }else {
            // 账号密码登录，没有注册功能
            return goLogin(loginVo,1);
        }
    }

    /**
     *
     * @param loginVo 登录信息
     * @param tag  0 邮箱登录 1：账号密码登录
     * @return
     */
    public Result goLogin(LoginVo loginVo,int tag){
        User user=new User();
        if(tag==1){
            //账号密码登录
            //为空校验
            if(StrUtil.isEmpty(loginVo.getUserName())){
                return Result.error(4000,"账号不能为空");
            }
            if(StrUtil.isEmpty(loginVo.getUserPassword())){
                return Result.error(4000,"密码不能为空");
            }

            user.setUserName(loginVo.getUserName());
            user=selectUserByParam(user);
            if(!SecureUtil.md5(loginVo.getUserPassword()).equals(user.getUserPassword())){
                return Result.error(4000,"密码错误");
            }
        }
        if (tag==0){
            //邮箱登录
            user.setUserEmail(loginVo.getUserEmail());
            user=selectUserByParam(user);
        }
        //走到这里代表登录成功，进行添加T币，更新登录时间，返回用户信息和token
        String today = DateUtil.today();
        String lastLoginDate = DateUtil.format(user.getLoginDate(), "yyyy-MM-dd");
        if(!today.equals(lastLoginDate)){ //每日登录获得登录奖励
            Coin coin=coinMapper.selectByOwner(user.getUserId());
            coin.setCoinNum(coin.getCoinNum()+1.0);  //登录获得 1 个硬币
            coinMapper.updateByPrimaryKey(coin);
            // 这里添加登录奖励
            Record record=new Record();
            record.setRecordId(UUID.randomUUID().toString());
            record.setRecordCreatetime(new Date());
            record.setRecordDescribe("登录奖励");
            record.setCoinChange("+1");
            record.setWorksId("-1");
            record.setAuthorId(user.getUserId());
            record.setWorksType(-1);
            recordMapper.insertSelective(record);
        }
        user.setLoginDate(DateUtil.parse(today));
        userMapper.updateByPrimaryKey(user);

        String token = jwtUtil.generateToken(user.getUserId().toString());
        redisUtil.set("user:"+user.getUserId(),token,59*60*24);

        Map<String,String> userInfo=new HashMap<>();
        userInfo.put("uid",user.getUserId().toString());
        userInfo.put("role",user.getUserRole());
        userInfo.put("nickname",user.getNickName());
        userInfo.put("Avatar",user.getUserAvatar());
        return Result.success(MapUtil.builder().put("token",token).put("userInfo",userInfo).build());
    }

    /**
     *
     * @param loginVo 登录信息
     * @return
     */
    public Result goRegister(LoginVo loginVo){
        User user=new User();
        user.setUserEmail(loginVo.getUserEmail());
        user.setUserAvatar("http://localhost:88/resource/img/panda.png");
        user.setUserName(loginVo.getUserEmail());
        user.setNickName("Coder_"+ RandomUtil.randomNumbers(6));
        user.setUserSex("保密");
        user.setUserRole("ROLE_user");
        user.setUserSign("这个家伙很懒，什么都没有留下！");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i=userMapper.insertSelective(user);
        if(i>0){
            user=userMapper.selectUserByParam(user); // 插入后自动生成主键id
            // 注册成功后，初始化硬币和收藏夹
            Coin coin=new Coin();
            coin.setCoinId(UUID.randomUUID().toString());
            coin.setCoinNum(100.0);
            coin.setCoinCreatetime(new Date());
            coin.setCoinUpdatetime(new Date());
            coin.setCoinOwner(user.getUserId());
            coinMapper.insertSelective(coin);
            Favorite favorite=new Favorite();
            favorite.setFavoriteOwner(user.getUserId());
            favorite.setFavoriteId(UUID.randomUUID().toString());
            favorite.setFavoriteName("默认收藏夹");
            favorite.setFavoriteCreatetime(new Date());
            favoriteMapper.insertSelective(favorite);
            //注册成功，转到登录
            return goLogin(loginVo,0);
        }
        return Result.error(4000,"账号注册失败!");
    }

}

