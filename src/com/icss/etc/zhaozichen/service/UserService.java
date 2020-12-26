package com.icss.etc.zhaozichen.service;

import java.util.List;

import com.icss.etc.zhaozichen.pojo.Student;

public interface UserService {
	
	
	
	//增加
	public void insertUser(Student student);
	//查询全部
	public List<Student> selectAllUser(Student student);
	//更新
	public void updateUser(Student student);
	//删除
	public void deleteUser(Student student);

}
