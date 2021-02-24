package com.gitee.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author jie
 */
public interface UserService extends IService<User>, UserDetailsService {
}
