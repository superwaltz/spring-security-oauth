package com.liam.oauth2.service;

import com.liam.oauth2.entity.User;
import com.liam.oauth2.repository.BaseRepository;
import com.liam.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: liangzy
 * @date: 2019/03/15 下午2:52
 * @desc:
 */

@Service
public class UserService extends BaseService<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseRepository getRepository() {
        return this.userRepository;
    }

    public boolean save(){
        User user = new User();
        user.setName("张三");
        return super.save(user);
    }

}
