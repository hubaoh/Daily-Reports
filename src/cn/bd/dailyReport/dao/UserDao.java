package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

	// 新增用户
	@Insert("insert into user (id,employeeNumber, userName, password,roleValue, deptName,phoneNumber,createTime, createUser,gender) values(NULL,#{employeeNumber}, #{userName}, #{password}, #{roleValue}, #{deptName}, #{phoneNumber}, #{createTime}, #{createUser},#{gender})")
	 void create(User user);
	// 删除用户
	@Delete("delete from user where id=#{id}")
	 void delete(Long id);
	// 修改用户信息
	@Update("update user set userName=#{userName}, employeeNumber=#{employeeNumber}, deptName=#{deptName}, roleValue=#{roleValue}, phoneNumber=#{phoneNumber} where id = #{id}")
    public void update(User user);

	// 完善用户信息
	@Update("update user set phoneNumber=#{phoneNumber}, address=#{address}, qq=#{qq}, hobby=#{hobby},idCard=#{idCard},likeNumber=#{likeNumber} where id = #{id}")
	void updatePersonalInfo(User user);

	// 修改密码
	@Update("update user set password=#{password} where id=#{id}")
	void updatePassWord(@Param("id") Long id, @Param("password") String password);

    // 获得所有用户
    public List<User> list(@Param("page") int page, @Param("pageSize") int pageSize, @Param("employeeNumber") String employeeNUmber, @Param("userName") String userName, @Param("deptName") String deptName);

    // 获得单个用户

	@Select("select * from user where id = #{id}")
     User findOne(@Param("id") Long id);

	// 修改用户的所属部门名称
	@Update("update user set deptName = #{newDeptName} where deptName = #{deptName}")
	void updateDeptName(@Param("deptName") String deptName, @Param("newDeptName") String newDeptName);

	// 找回密码
	@Select("select * from user where userName=#{userName} and phoneNumber=#{phoneNumber} and idCard=#{idCard} and likeNumber=#{likeNumber}")
	User findPassWord(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber, @Param("idCard") String idCard, @Param("likeNumber") String likeNumber);

	// 返回全部用户
	@Select("select * from user WHERE employeeNumber LIKE concat('%',#{employeeNumber},'%') AND userName LIKE concat('%',#{userName},'%') AND deptName LIKE concat('%',#{deptName},'%')")
	List<User> getAll(@Param("employeeNumber") String employeeNUmber, @Param("userName") String userName, @Param("deptName") String deptName);
	
}
