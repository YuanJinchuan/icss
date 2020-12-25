package com.icss.etc.zhaozichen.pojo;

import java.io.Serializable;

import com.icss.etc.zhaozichen.turbine.Col;
import com.icss.etc.zhaozichen.turbine.Key;
import com.icss.etc.zhaozichen.turbine.Table;
import com.sun.org.glassfish.external.arc.Taxonomy;
//这里实现Serializable接口 考虑以后可能用scoket直接传输实体 
@Table("Student")
public class Student implements Serializable{
	@Key("ID")
	@Col("ID")
	private String id;
	
	@Col("NAME")
	private String name;
	
	@Col("AGE")
	private String age;
	
	@Col("DAY")
	private String day;



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", day=" + day + "]";
	}
	
	
	
	
	
	
	

}
