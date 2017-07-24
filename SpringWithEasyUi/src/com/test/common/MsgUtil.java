package com.test.common;

public class MsgUtil {
	
	/**
	 * @param e 异常信息
	 * @return  需要返回调用方的报文
	 */
	public static ReturnMessage genErrMsg(Exception e){
		
		ReturnMessage msg = new ReturnMessage();
		
		msg.setMessageType("E");
		msg.setMessageData(e.getMessage());
		msg.setReturnCode("S999");
		if(e instanceof BusException){
			msg.setReturnCode(((BusException)e).getReturnCode());
		}
		
		return msg;
	}
	
	/**
	 * @param str 返回报文内容
	 * @return    需要返回调用方的报文
	 */
	public static ReturnMessage genNormalMsg(String str){
		
		ReturnMessage msg = new ReturnMessage();
		
		msg.setMessageType("N");
		msg.setReturnCode("0000");
		msg.setMessageData(str);
		
		return msg;
		
	}
}
