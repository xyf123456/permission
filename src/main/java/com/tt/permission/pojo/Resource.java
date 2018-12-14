package com.tt.permission.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: com.tt.permission.pojo.Resource
 * @Description: 资源实体类
 * @Author: Administrator
 * @CreateDate: 2018/12/14 9:45
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Entity
@Table(name = "sys_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceid;
    @Column
    private Integer resourcepid;
    @Column
    private String resourcename;
    @Column
    private String resourceicon;
    @Column
    private String resourceurl;

//    @Transient作用是指定该属性或字段不是永久的。 它用于注释实体类，映射超类或可嵌入类的属性或字段。
    @OneToMany(cascade = {CascadeType.ALL})
    @Transient
    private List<Resource> children;

    public Resource() {
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public Integer getResourcepid() {
        return resourcepid;
    }

    public void setResourcepid(Integer resourcepid) {
        this.resourcepid = resourcepid;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getResourceicon() {
        return resourceicon;
    }

    public void setResourceicon(String resourceicon) {
        this.resourceicon = resourceicon;
    }

    public String getResourceurl() {
        return resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
