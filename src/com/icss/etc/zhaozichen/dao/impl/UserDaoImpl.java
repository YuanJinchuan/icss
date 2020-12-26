package com.icss.etc.zhaozichen.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.icss.etc.zhaozichen.dao.UserDao;
import com.icss.etc.zhaozichen.pojo.Student;
import com.icss.etc.zhaozichen.turbine.BeanUtil;
import com.icss.etc.zhaozichen.turbine.DbUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void insertUser(Student student) {
		DbUtil<Student> dbUtil=new DbUtil<>();
		try {
			dbUtil.excuteUpdate(BeanUtil.getStudentBeanColsSql(student, "insert"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> selectAllUser(Student student) {
		List<Student> list=new ArrayList<Student>();
		DbUtil<Student> dbUtil=new DbUtil<>();
		try {
			list=dbUtil.excuteSelect(BeanUtil.getStudentBeanColsSql(student, "select"), student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void updateUser(Student student) {
		DbUtil<Student> dbUtil=new DbUtil<>();
		try {
			dbUtil.excuteUpdate(BeanUtil.getStudentBeanColsSql(student, "update"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(Student student) {
		DbUtil<Student> dbUtil=new DbUtil<>();
		try {
			dbUtil.excuteUpdate(BeanUtil.getStudentBeanColsSql(student, "delete"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


}
