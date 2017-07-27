package com.test.common;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	/**
	 * @param o - 需要获取的对象
	 * @param request - Http传入的HttpServletRequest对象
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getRequestObject(Class<?> c,HttpServletRequest request) throws Exception{
		
		Object o = c.newInstance();
		
		Field[] fields = c.getDeclaredFields();
		//获取类型
		for(Field f : fields){
			//检查栏位是否在HttpServletRequest中存在
			String fieldName = f.getName();
			String str = request.getParameter(fieldName);
			if(str != null && !str.isEmpty()){
				Object value  = getValue(f,str);
				if(value != null){
					f.setAccessible(true);
					f.set(o, value);
				}
			}
		}
		
		return o;
	}
	
	private static Object getValue(Field f,String s){
		
		Class<?> c = f.getType(); 
		
		if(c.equals(String.class)){
			return s;
		}
		
		if(c.equals(int.class)|| c.equals(Integer.class)){
			return Integer.valueOf(s);
		}
		
		if(c.equals(long.class)|| c.equals(Long.class)){
			return Long.valueOf(s);
		}
		
		if(c.equals(float.class)|| c.equals(Float.class)){
			return Float.valueOf(s);
		}
		
		if(c.equals(double.class)|| c.equals(Double.class)){
			return Double.valueOf(s);
		}
		
		if(c.equals(boolean.class) || c.equals(Boolean.class)){
			return Boolean.valueOf(s);
		}
		//如果使用SimpleDateFormat，需要知道日期的具体格式
		if(c.equals(Date.class)){
			return new Date(s);
		}
		return null;
	}
}
