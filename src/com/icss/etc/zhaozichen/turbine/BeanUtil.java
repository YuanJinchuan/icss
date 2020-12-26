package com.icss.etc.zhaozichen.turbine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.icss.etc.zhaozichen.exception.ETCSQlException;
import com.icss.etc.zhaozichen.pojo.Student;

public class BeanUtil {


	/**
	 * 注解解析并生成简易增删改查sql语句
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static String getStudentBeanColsSql(Object student, String type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//获取class对象 
		Class<? extends Object> clazz = student.getClass();
		//获取field数组 用于遍历属性注解
		Field[] fields = clazz.getDeclaredFields();
		
		String sql="";
		//拼接sql语句头部
		StringBuilder sbh = new StringBuilder();
		//拼接sql语句尾部
		StringBuilder sbb = new StringBuilder();
		if ("select".equals(type)) {
			sbh.append("select * from ");
		}else if("insert".equals(type)) {
			sbh.append("insert into  ");
		}else if("update".equals(type)){
			sbh.append("update ");
		}else if("delete".equals(type)){
			sbh.append("delete from ");
		}

		// 表名
		//获取table注解中的内容作为数据库表明
		Table table= clazz.getAnnotation(Table.class);
		//语句中加入表明
		sbh.append(table.value()+" ");
		
		//查询
		if("select".equals(type)) {
			//查询默认先查全部
			sql=sbh.toString();
		//添加
		}else if("insert".equals(type)) {
			// 字段
			sbh.append("(");
			sbb.append("values(");
			//拼接insertsql 思路 目标是生成insert语句 insert into xxx （列名）values（数据值）
			for (Field field : fields) {
				//查找col注解
				if (field.isAnnotationPresent(Col.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					Col col = field.getAnnotation(Col.class);
					//头部拼接列明
					sbh.append(col.value()+",");
					//声明调用的方法名
					Method m = clazz.getDeclaredMethod(convertToGetMethod(field.getName()));
					//调用get方法获取值 
					Object o= m.invoke(student);
					//作为数据值拼接入尾部
					if(o!=null) {
						sbb.append("'"+o+"',");
					}else {
						sbb.append(o+",");
					}
					
				} else {
					continue;
				}

			}
			//整理字符串
			sql=sbh.substring(0, sbh.lastIndexOf(","))+")"+sbb.substring(0, sbb.lastIndexOf(","))+")";
		//更新
		}else if("update".equals(type)){
			sbh.append("  set ");
			//拼接updatesql 思路 目标是生成update语句 update xxx set （列名）=（数据值） where id=主键值
			for (Field field : fields) {
				//查找col注解
				if (field.isAnnotationPresent(Col.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					Col col = field.getAnnotation(Col.class);
					sbh.append(col.value()+"=");
					Method m = clazz.getDeclaredMethod(convertToGetMethod(field.getName()));
					Object o= m.invoke(student);
					if(o!=null) {
						sbh.append("'"+o+"',");
					}else {
						sbh.append(o+",");
					}
				} 
				//查找key注解 （主键）
				if (field.isAnnotationPresent(Key.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					Key key = field.getAnnotation(Key.class);
					Method m = clazz.getDeclaredMethod(convertToGetMethod(field.getName()));
					Object o= m.invoke(student);
					//主键为空时抛出自定义异常
					if(o==null) {throw new ETCSQlException("主键为空");}
					sbb.append(" where "+key.value()+"="+o);
				} 
			}
			//整理字符串
			sql=sbh.substring(0, sbh.lastIndexOf(","))+sbb.toString();
		}else if("delete".equals(type)){
			//拼接deletesql 思路 目标是生成update语句 delete xxx  where id=主键值
			for (Field field : fields) {
				//查找key注解
				if (field.isAnnotationPresent(Key.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					Key key = field.getAnnotation(Key.class);
					Method m = clazz.getDeclaredMethod(convertToGetMethod(field.getName()));
					Object o= m.invoke(student);
					if(o==null) {throw new ETCSQlException("主键为空");}
					sbb.append(" where "+key.value()+"="+o);
				} 
			}
			
			sql=sbh.toString()+sbb.toString();
		}else {
			//其他情况暂不考虑 统统认为参数不合法
			throw new ETCSQlException("参数有误");
		}

		return sql;
	}
	
	

	
	/**
	 * 通过属性名获取get方法名
	 * */
	private static String convertToGetMethod(String name) {
		String head=name.substring(0,1).toUpperCase();
		String body=name.substring(1);
		return "get"+head+body;
	}

}
