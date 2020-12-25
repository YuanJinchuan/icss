package com.icss.etc.zhaozichen.service.impl;

import com.icss.etc.zhaozichen.dao.UserDao;
import com.icss.etc.zhaozichen.dao.impl.UserDaoImpl;
import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.service.UserService;

public class UserServiceImpl implements UserService{
	
	
	
	private UserDao dao=new UserDaoImpl();
	
	
	

	@Override
	public boolean insertOneUser(Student student) {
		
		return dao.insertUser(student);
	}

}
