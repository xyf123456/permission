package com.tt.permission.dao;

import com.tt.permission.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName: com.tt.permission.dao.UserRepository
 * @Description: 用户数据访问接口
 * @Author: Administrator
 * @CreateDate: 2018/12/14 8:36
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>{

    User findUserByUsernameAndPassword(String name,String pwd);

    @Query(value = "select * from sys_user WHERE username=?1",nativeQuery = true)
    User findByUsername(String username);
}
