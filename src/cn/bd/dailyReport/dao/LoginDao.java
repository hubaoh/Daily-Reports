package cn.bd.dailyReport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bd.dailyReport.model.User;

public interface LoginDao {

	// 登陆 通过输入的用户名以及密码查询登陆的账号是否存在
	public User login(@Param("userName") String userName, @Param("password") String password);
	
	
	
}
