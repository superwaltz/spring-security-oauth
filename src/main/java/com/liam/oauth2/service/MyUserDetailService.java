package com.liam.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liangzy
 * @date: 2019/03/14 下午5:01
 * @desc:
 */

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===============");
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("default");
        List<SimpleGrantedAuthority> list = new ArrayList();
        list.add(authority);
        User user = new User("lzy",passwordEncoder.encode("123"),list);
        return user;
    }
}
