package com.icss.etc.zhaozichen.controller;

import java.util.ArrayList;
import java.util.List;

import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.service.UserService;
import com.icss.etc.zhaozichen.service.impl.UserServiceImpl;

public class UserController {
	

	private UserService service=new UserServiceImpl();
 //处理客户端请求
	public List<Student> service(Object msg) {
		
		List<Student> students=new ArrayList<Student>();
		
		Student student=(Student) msg;
		
		//查询请求不包含逗号
		if("1".equals(student.getType())) {
			//添加方法
			service.insertUser(student);
		}else if("2".equals(student.getType())) {
			//查询方法
			students=service.selectAllUser(new Student());
		}else if("3".equals(student.getType())) {
			//修改方法
			service.updateUser(student);
		}else if("4".equals(student.getType())) {
			//删除方法
			service.deleteUser(student);;
		}else {
			//查询方法
			students=service.selectAllUser(new Student());
		}
		
		return students;
	}
	
	
	
	

}
