package cn.bd.dailyReport.utils.dto;

import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.model.Role;
import cn.bd.dailyReport.model.User;

import java.util.Date;

/**
 * 用来存放前端用户表中的数据
 *
 * Created by 赵传志 on 2018/2/2.
 */
public class UserDto {

    private Long id; // 序号

    private String employeeNumber; // 员工编号

    private String userName; // 用户名

    private String password; // 密码

    private String deptName; // 部门名称

    private String phoneNumber; // 手机号码

    private String gender; // 性别

    private String address; // 地址

    private String qq; // qq

    private String hobby; // 爱好

    private Date birthday; // 出生日期

    private Date createTime; // 该用户的创建时间（入职时间）

    private String createUser; // 该用户由谁创建

    private Role role; // 用户权限

    private String roleName; // 权限名称

    private Dept dept; // 部门

    public UserDto() {
    }

    public UserDto(User user, Role role) {
        this.id = user.getId();
        this.employeeNumber = user.getEmployeeNumber();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.deptName = user.getDeptName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.qq = user.getQq();
        this.hobby = user.getHobby();
        this.birthday = user.getBirthday();
        this.createTime = user.getCreateTime();
        this.createUser = user.getCreateUser();
        this.gender = user.getGender();
        this.role = role;
        this.roleName = role.getRoleName();
    }

    public UserDto(User user, Role role, Dept dept) {
        this.id = user.getId();
        this.employeeNumber = user.getEmployeeNumber();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.deptName = user.getDeptName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.roleName = role.getRoleName();
        this.qq = user.getQq();
        this.hobby = user.getHobby();
        this.birthday = user.getBirthday();
        this.createTime = user.getCreateTime();
        this.createUser = user.getCreateUser();
        this.gender = user.getGender();
        this.role = role;
        this.dept = dept;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
