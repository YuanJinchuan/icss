package com.icss.etc.zhaozichen.turbine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

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
	public static String getStudentBeanCols(Student student, String type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<Student> clazz = Student.class;
		Field[] fields = clazz.getDeclaredFields();
		
		String sql="";
		
		StringBuilder sbh = new StringBuilder();
		StringBuilder sbb = new StringBuilder();
		if ("select".equals(type)) {
			sbh.append("select * from ");

		}else if("insert".equals(type)) {
			sbh.append("insert into  ");
		}

		// 表名
		Table table= clazz.getAnnotation(Table.class);
		sbh.append(table.value()+" ");

		if("select".equals(type)) {
			
			
			
		}else if("insert".equals(type)) {
			// 字段
			sbh.append("values(");
			Method m = clazz.getDeclaredMethod("getName");
			
			 Object o= m.invoke(student);
			 sbh.append(o.toString());
			//sbh.substring(0, sbh.lastIndexOf(","));
			sbh.append(")");
			sql=sbh.toString();
		} else {
			// 字段
			for (Field field : fields) {
				if (field.isAnnotationPresent(Col.class)) {

					if (!field.isAccessible()) {
						field.setAccessible(true);
						;
					}
					Col col = field.getAnnotation(Col.class);

				} else {
					continue;
				}

			}
			
			
		}
        
		if("".equals(sql)) {
			throw new ETCSQlException("参数有误");
		}

		return sql;
	}

}
