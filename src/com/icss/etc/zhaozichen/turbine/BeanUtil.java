package com.icss.etc.zhaozichen.turbine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.icss.etc.zhaozichen.exception.ETCSQlException;
import com.icss.etc.zhaozichen.pojo.Student;

public class BeanUtil {

	/**
	 * 注解解析
	 */
	public static <T> T getBean(Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (!field.isAnnotationPresent(Autowired.class))
					continue;
				if (!field.isAccessible()) {
					field.setAccessible(true);
					;
				}
				field.set(obj, field.getType().newInstance());
			}
		} catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

	/**
	 * 注解解析
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static String getStudentBeanColsSql(Object student, String type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<? extends Object> clazz = student.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		String sql="";
		
		StringBuilder sbh = new StringBuilder();
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
		Table table= clazz.getAnnotation(Table.class);
		sbh.append(table.value()+" ");
		
		//查询
		if("select".equals(type)) {
			
			sql=sbh.toString();
		//添加
		}else if("insert".equals(type)) {
			// 字段
			sbh.append("(");
			sbb.append("values(");
			//拼接insertsql
			for (Field field : fields) {
				if (field.isAnnotationPresent(Col.class)) {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					Col col = field.getAnnotation(Col.class);
					sbh.append(col.value()+",");
					Method m = clazz.getDeclaredMethod(convertToGetMethod(field.getName()));
					Object o= m.invoke(student);
					if(o!=null) {
						sbb.append("'"+o+"',");
					}else {
						sbb.append(o+",");
					}
					
				} else {
					continue;
				}

			}

			sql=sbh.substring(0, sbh.lastIndexOf(","))+")"+sbb.substring(0, sbb.lastIndexOf(","))+")";
		//更新
		}else if("update".equals(type)){
			sbh.append("  set ");
			
			for (Field field : fields) {
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
			;
			sql=sbh.substring(0, sbh.lastIndexOf(","))+sbb.toString();
		}else if("delete".equals(type)){
			for (Field field : fields) {
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
			throw new ETCSQlException("参数有误");
			
			// 字段
//			for (Field field : fields) {
//				if (field.isAnnotationPresent(Col.class)) {
//
//					if (!field.isAccessible()) {
//						field.setAccessible(true);
//						;
//					}
//					Col col = field.getAnnotation(Col.class);
//
//				} else {
//					continue;
//				}
//
//			}
			
			
		}
        
		if("".equals(sql)) {
			throw new ETCSQlException("参数有误");
		}

		return sql;
	}
	
	

	
	
	private static String convertToGetMethod(String name) {
		String head=name.substring(0,1).toUpperCase();
		String body=name.substring(1);
		return "get"+head+body;
	}

}
