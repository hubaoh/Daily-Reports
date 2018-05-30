package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * 权限类
 * 
 * @author 赵传志
 *
 */
public class Role {

	private Long id;

	private String roleName; // 权限名称

	private String roleValue; // 权限代码 比如：01 管理员 02 普通用户 03 BOSS 04 访客

	private Date createTime; // 该用户的创建时间（入职时间）

	private String createUser; // 该用户由谁创建

	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
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
		return "Role{" +
				"id=" + id +
				", roleName='" + roleName + '\'' +
				", roleValue='" + roleValue + '\'' +
				", createTime=" + createTime +
				", createUser='" + createUser + '\'' +
				'}';
	}
}
