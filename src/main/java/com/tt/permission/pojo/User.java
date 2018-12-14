package com.tt.permission.pojo;

import javax.persistence.*;

/**
 * @ClassName: com.tt.permission.pojo.User
 * @Description: 用户实体类
 * @Author: Administrator
 * @CreateDate: 2018/12/14 8:33
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    private String username;

    private String password;

    // 关联角色
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "roleid", foreignKey = @ForeignKey(name = "none"))
    private Role role;

    public User() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
