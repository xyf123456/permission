package com.tt.permission.pojo;

import javax.persistence.*;

/**
 * @ClassName: com.tt.permission.pojo.RoleResource
 * @Description: 角色资源实体类
 * @Author: Administrator
 * @CreateDate: 2018/12/14 10:22
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Entity
@Table(name = "sys_role_resource")
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleresourceid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleid", foreignKey = @ForeignKey(name = "none"))
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resourceid", foreignKey = @ForeignKey(name = "none"))
    private Resource resource;

    public RoleResource() {
    }

    public Integer getRoleresourceid() {
        return roleresourceid;
    }

    public void setRoleresourceid(Integer roleresourceid) {
        this.roleresourceid = roleresourceid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
