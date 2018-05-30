package cn.bd.dailyReport.model;

import cn.bd.dailyReport.utils.dto.UserDto;

import java.util.Date;

/**
 * 该管理系统的用户类
 * @author 赵传志
 *
 */
public class User {

	private Long id; // 序号

	private String employeeNumber; // 员工编号
	
	private String userName; // 用户名
	
	private String password; // 密码
	
	private String deptName; // 部门名称
	
	private String phoneNumber; // 手机号码
	
	private String roleValue; // 用户拥有的权限

	private String gender; // 性别
	
	private String address; // 地址
	
	private String qq; // qq
	
	private String hobby; // 爱好
	
	private Date birthday; // 出生日期

	private String idCard; // 身份证后六位

	private String likeNumber; // 最喜欢的数字
	
	
	private Date createTime; // 该用户的创建时间（入职时间）
	
	private String createUser; // 该用户由谁创建

	public User() {
		super();
	}

	public User(UserDto userDto) {
		this.employeeNumber = userDto.getEmployeeNumber();
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.deptName = userDto.getDept().getDept_name();
		this.roleValue = userDto.getRole().getRoleValue();
		this.phoneNumber = userDto.getPhoneNumber();
		this.createTime = new Date();
		this.createUser = userDto.getCreateUser();
	}

	public User(UserDto userDto, Long id){
		this.id = id;
		this.employeeNumber = userDto.getEmployeeNumber();
		this.userName = userDto.getUserName();
		this.deptName = userDto.getDept().getDept_name();
		this.roleValue = userDto.getRole().getRoleValue();
		this.phoneNumber = userDto.getPhoneNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(String likeNumber) {
		this.likeNumber = likeNumber;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", employeeNumber='" + employeeNumber + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", deptName='" + deptName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", roleValue='" + roleValue + '\'' +
				", gender='" + gender + '\'' +
				", address='" + address + '\'' +
				", qq='" + qq + '\'' +
				", hobby='" + hobby + '\'' +
				", birthday=" + birthday +
				", createTime=" + createTime +
				", createUser='" + createUser + '\'' +
				'}';
	}
}
