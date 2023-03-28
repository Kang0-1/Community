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
import com.kang.mapper.CoinMapper;
import com.kang.mapper.FavoriteMapper;
import com.kang.mapper.RecordMapper;
import com.kang.mapper.UserMapper;
import com.kang.service.UserService;
import com.kang.domain.Result;
import com.kang.utils.JwtUtil;
import com.kang.utils.MailUtil;
import com.kang.utils.RedisUtil;
import org.springframework.stereotype.Service;

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

    @Override
    public Map<Object, Object> getImgCode() {
        String key = UUID.randomUUID().toString();
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(200, 80);
        shearCaptcha.setGenerator(randomGenerator);
        String code = shearCaptcha.getCode();
        byte[] imageBytes = shearCaptcha.getImageBytes();
        String base64Img = "data:image/jpg;base64," + Base64.encode(imageBytes);
        //  方便测试 code = "1234";
        redisUtil.set("captcha:" + key, code, 60);
        return MapUtil.builder().put("key", key).put("captchaImg", base64Img).build();
    }

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

