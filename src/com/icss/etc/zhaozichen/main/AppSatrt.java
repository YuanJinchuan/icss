package com.icss.etc.zhaozichen.main;

import com.icss.etc.zhaozichen.controller.UserController;
import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.turbine.BeanUtil;

public class AppSatrt {
	
	
	
	
	
	public static void main(String[] args) {
		
		
		UserController u=BeanUtil.getBean(UserController.class);
		Student student=new Student();
		
		System.out.println(u.addUser(student));
		
	}

}
