package com.corejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyObj implements InvocationHandler {
	
	private Target target;
	
	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before " + method.getName());
		Object o = method.invoke(target, args);
		System.out.println("after " + method.getName());
		return o;
	}

}
