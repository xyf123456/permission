package com.tt.permission.service;

import com.tt.permission.dao.UserRepository;
import com.tt.permission.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: com.tt.permission.service.UserServiceImpl
 * @Description: 用户业务接口 实现类
 * @Author: Administrator
 * @CreateDate: 2018/12/14 8:35
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean login(User user) throws Exception {
        boolean flag = false;
        if (userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword()) != null) {
            flag = true;
        }
        return flag;
    }
}
