package com.corejava.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProxyObj po = new ProxyObj();
		TargetImpl to = new TargetImpl();
		to.setName("liu");
		po.setTarget(to);
		Target pt = (Target)Proxy.newProxyInstance(to.getClass().getClassLoader(), to.getClass().getInterfaces(), po);
		
		pt.sayHi();
		pt.sayBy();
		pt.sayName();
	}

}
