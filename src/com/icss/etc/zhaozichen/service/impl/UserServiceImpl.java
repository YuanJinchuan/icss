package com.icss.etc.zhaozichen.service.impl;

import java.util.List;

import com.icss.etc.zhaozichen.dao.UserDao;
import com.icss.etc.zhaozichen.dao.impl.UserDaoImpl;
import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.service.UserService;

public class UserServiceImpl implements UserService{
	
	
	
	private UserDao dao=new UserDaoImpl();

	@Override
	public void insertUser(Student student) {
		dao.insertUser(student);
		
	}

	@Override
	public List<Student> selectAllUser(Student student) {
		return dao.selectAllUser(student);
	}

	@Override
	public void updateUser(Student student) {
		dao.updateUser(student);
		
	}

	@Override
	public void deleteUser(Student student) {
		dao.deleteUser(student);
		
	}
	
	
	



}
