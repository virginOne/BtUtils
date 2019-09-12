package com.zjj.bt.dht.krpc;

/**
 *ClassName:ErrorCode
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月12日
 *@Version 1.0
 */
public enum ErrorCode {
	_201(201,"Generic Error"),
	_202(202,"Server Error"),
	_203(203,"Protocol Error, such as a malformed packet, invalid arguments, or bad token"),
	_204(204,"Method Unknown");
	
	private int code;
	private String msg;
	
	private ErrorCode(int code, String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	@Override
	public String toString() {
		return "Error code:"+code+",ErrorMessage:"+msg+".";
	}
}
