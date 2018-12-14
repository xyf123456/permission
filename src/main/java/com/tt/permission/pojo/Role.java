package com.tt.permission.pojo;

import javax.persistence.*;

/**
 * @ClassName: com.tt.permission.pojo.Role
 * @Description: 角色实体类
 * @Author: Administrator
 * @CreateDate: 2018/12/14 10:02
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;
    @Column
    private String rolename;

    public Role() {
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
