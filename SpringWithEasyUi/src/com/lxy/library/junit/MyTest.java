package com.lxy.library.junit;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import org.junit.Test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.lxy.library.common.GsonExclusionStrategy;
import com.lxy.library.entry.Book;
import com.lxy.library.entry.BookClassify;

public class MyTest {
	@Test
	public void gsonTest() {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Map<String,String> m = new HashMap<String,String>();
		m.put("name", "aaa");
		m.put("value", "bbb");
		System.out.println(new GsonBuilder().create().toJson(m));
	}
	@Test
	public void annontionTest(){
		Class c = Book.class;
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields){
			if(f.isAnnotationPresent(Expose.class)){
					System.out.println(f.getName());
			}
		}
	}
	
	@Test
	public void gsonExcludeTest(){
		Book b = new Book();
		b.setBookId("11111");
		b.setBookAuthor("22222");
		b.setBookImageUrl("33333");
		b.setBookDescription("44444");
		b.setBookPrice(55);
		b.setBookPublish("666666");
		b.setBookStatus("N");
		b.setBookName("77777");
		BookClassify bc = new BookClassify();
		bc.setClassifyId(1);
		bc.setClassifyName("88888");
		bc.setCreateDate(new Date());
		bc.setLastModifyDate(new Date());
		b.setBookClassify(bc);
		
		GsonExclusionStrategy exc = new GsonExclusionStrategy("bookSet");
		exc.setSkipAnnotationList(Id.class);
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(exc).create();
		System.out.println(gson.toJson(b));
	}

}
