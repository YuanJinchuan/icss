package com.icss.etc.zhaozichen.turbine;

import java.lang.reflect.InvocationTargetException;

import com.icss.etc.zhaozichen.pojo.Student;

public class DbFactory {
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Student student =new Student();
		
		student.setName("12312");
		
		System.out.println("生成的sql>>：" + BeanUtil.getStudentBeanCols(student, "insert"));
	}
	
}
