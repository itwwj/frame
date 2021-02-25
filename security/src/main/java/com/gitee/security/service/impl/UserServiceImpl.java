package com.gitee.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.security.entity.User;
import com.gitee.security.mapper.UserMapper;
import com.gitee.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jie
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper mapper;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getUsername, s);
        User user = mapper.selectOne(lambdaQuery);
        if (user==null){
            throw new Exception("用户不存在");
        }
        List<GrantedAuthority> root = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), root);
    }
}
