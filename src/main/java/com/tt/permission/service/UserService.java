package com.tt.permission.service;
import com.tt.permission.pojo.User; /**
 * @ClassName: com.tt.permission.service.UserService
 * @Description:  用户业务接口
 * @Author:      Administrator
 * @CreateDate: 2018/12/14 8:35
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
public interface UserService {
    /**
     * @ Description:登录
     * @params:  * @Param: user
     * @return:boolean
     **/
    boolean login(User user)throws Exception;
}
