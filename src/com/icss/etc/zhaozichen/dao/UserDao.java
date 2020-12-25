package com.icss.etc.zhaozichen.dao;

import java.util.List;

import com.icss.etc.zhaozichen.pojo.Student;

public interface UserDao {
	
	
	
	public boolean insertUser(Student student);
	
	public List<Student> selectAllUser(Student student);

}
