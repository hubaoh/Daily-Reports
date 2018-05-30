package cn.bd.dailyReport.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bd.dailyReport.dao.LoginDao;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.LoginService;

@Service("loginService")
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public User login(String userName, String password) {
		System.out.println(loginDao);
		User user = loginDao.login(userName, password);
		System.out.println(user);
		return user;
	}

	

}
