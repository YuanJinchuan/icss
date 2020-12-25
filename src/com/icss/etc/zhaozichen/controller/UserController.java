package com.icss.etc.zhaozichen.controller;

import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.service.UserService;
import com.icss.etc.zhaozichen.service.impl.UserServiceImpl;

public class UserController {
	

	private UserService service=new UserServiceImpl();

	public boolean addUser(Student student) {

		return service.insertOneUser(student);
	}
	
	
	
	

}
