package com.icss.etc.zhaozichen.turbine;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtil<T> {

	private static Statement stmt = null;// 静态方法在类加载的时候就加载，所以stmt必须是static 类型
	private static Connection connection = null;

	public List<T> excuteSelect(String sql,T student) {
		Class<T> clazz =(Class<T>) student.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		List<T> list=new ArrayList<T>();
		try {
			// 1.加载jdbc驱动类
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取远程mysql连接（url（主要用到的是ip+port+databaseName）
			String url = "jdbc:mysql://172.16.4.196:3306/test";// 通过jdbx访问mysql数据库服务器，远程服务器ip为172.16.4.196
																// ，port为3306，数据库名称为 test
			// phpMyadmin中创建的可远程访问权限的用户的用户名和密码
			String user = "root";
			String password = "";
			connection = DriverManager.getConnection(url, user, password);// jdbc 连接上mysql服务器上的test数据库了
			// 3.开启sql称述，（Statement，PreparedStatement（？占位符形式）），执行sql语句
			stmt = connection.createStatement();
			stmt.execute(sql);
			ResultSet rs=  stmt.getResultSet();
			
            while (rs.next()) {//循环判断下一个结果集是否为空
            	T studentone =clazz.newInstance();
    			for (Field field : fields) {
    				if (field.isAnnotationPresent(Col.class)) {
    					if (!field.isAccessible()) {
    						field.setAccessible(true);
    					}
    					Col col = field.getAnnotation(Col.class);
    					//调用set方法添加参数
    					Method m = clazz.getDeclaredMethod(convertToSetMethod(field.getName()),String.class);
    					m.invoke(studentone,rs.getString(col.value()));
    					
    				} else {
    					continue;
    				}

    			}
    			list.add(studentone);
    			
            }
			
			
			
	
			// 4.获取结果集（只有查询有）
			// 5.对结果集进行操作（只有查询有）
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.对资源进行关闭(Resultset(此处没有)，stmt，connection（采用栈式关闭)) 不能放入一个try catch
			// 中，因为若一个执行失败，则其余资源也无法关闭
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;

	}
	
	
	
	public void excuteUpdate(String sql) {

		try {
			// 1.加载jdbc驱动类
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取远程mysql连接（url（主要用到的是ip+port+databaseName）
			String url = "jdbc:mysql://172.16.4.196:3306/test";// 通过jdbx访问mysql数据库服务器，远程服务器ip为172.16.4.196
																// ，port为3306，数据库名称为 test
			// phpMyadmin中创建的可远程访问权限的用户的用户名和密码
			String user = "root";
			String password = "";
			connection = DriverManager.getConnection(url, user, password);// jdbc 连接上mysql服务器上的test数据库了
			// 3.开启sql称述，（Statement，PreparedStatement（？占位符形式）），执行sql语句
			stmt = connection.createStatement();
			stmt.execute(sql);
			//ResultSet set=  stmt.getResultSet();
	
			// 4.获取结果集（只有查询有）
			// 5.对结果集进行操作（只有查询有）
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.对资源进行关闭(Resultset(此处没有)，stmt，connection（采用栈式关闭)) 不能放入一个try catch
			// 中，因为若一个执行失败，则其余资源也无法关闭
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	private static String convertToSetMethod(String name) {
		String head=name.substring(0,1).toUpperCase();
		String body=name.substring(1);
		return "set"+head+body;
	}

}
