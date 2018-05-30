package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * 部门类
 * Created by 赵传志 on 2018/2/1.
 */
public class Dept {

    private Long id;

    private String dept_name; // 部门名称

    private String dept_number; // 部门编号

    private Date createTime; // 创建时间

    private String createUser; // 创建人

    public Dept() {
    }

    public Dept(String dept_name, String dept_number, String createUser) {
        this.dept_name = dept_name;
        this.dept_number = dept_number;
        this.createTime = new Date();
        this.createUser = createUser;
    }

    public Dept(Long id, String dept_name, String dept_number, Date createTime, String createUser) {
        this.id = id;
        this.dept_name = dept_name;
        this.dept_number = dept_number;
        this.createTime = createTime;
        this.createUser = createUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_number() {
        return dept_number;
    }

    public void setDept_number(String dept_number) {
        this.dept_number = dept_number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", dept_name='" + dept_name + '\'' +
                ", dept_number='" + dept_number + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
