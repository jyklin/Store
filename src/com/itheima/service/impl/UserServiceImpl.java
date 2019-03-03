package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService{
  //用户注册
	public void regist(User user) throws Exception{
		UserDao dao = new UserDaoImpl();
		dao.add(user);
		//发送邮件
		
		//email：收件人地址   emailMsg：邮件的内容
		String emailMsg ="欢迎您注册成为我们的一员，<a href='http://localhost/Sstore/user?method=acitive&code="+user.getCode()+"'>点次激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	/*
	 * 用户激活
	 * */
	@Override
	public User active(String code) throws Exception    {
		 UserDao dao = new UserDaoImpl();
		 //1.通过code获取一个用户
		 User user = dao.getByCode(code);
		 
		 //2.判断用户是否为空
		 if(user ==null) {
			 return null;
		 }
		 //3.修改用户状态   设置用户状态码
		 user.setState(1);
		 
		 dao.update(user);
		return user;
	}
	
	//用户登录

	@Override
	public User login(String username, String password) throws Exception {
		 UserDao dao = new UserDaoImpl();
		
		 return  dao.getByUsernameAndPwd(username,password);
		 
	}
}
