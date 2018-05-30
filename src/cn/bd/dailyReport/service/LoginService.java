package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.User;

public interface LoginService {

	// 登陆 通过输入的用户名以及密码查询登陆的账号是否存在
	public User login(String name, String password);

}
