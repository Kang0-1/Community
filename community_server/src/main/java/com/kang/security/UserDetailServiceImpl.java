package com.kang.security;

import com.kang.entity.User;
import com.kang.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author kang
 * @description TODO
 * @date 2023/3/23 21:08
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=new User();
        user.setUserName(username);
        user=userService.selectUserByParam(user);
        if(null==user){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // TODO 查询用户的权限信息
        return new AccountUser(user.getUserId(), user.getUserPassword(),user.getUserName(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUserRole()));
    }
}
