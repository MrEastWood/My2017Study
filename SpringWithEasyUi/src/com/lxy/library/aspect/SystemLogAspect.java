package com.lxy.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemLogAspect {
	@Pointcut("execution(* com.lxy.library.controller.*.*(..))")
	public void needLog(){
		
	}
	
	@After("needLog()")
	public void logInfo(){
		System.out.println("------- logining --------------");
	}
	
}
