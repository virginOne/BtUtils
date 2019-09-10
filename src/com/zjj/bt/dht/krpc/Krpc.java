package com.zjj.bt.dht.krpc;

import java.util.Map;

/**
 *ClassName:Krpc
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月10日
 *@Version 1.0
 */
public abstract class Krpc {
	private String t;
	private String y;
	private String q;
	private Map<String,Object> a;
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public Map<String, Object> getA() {
		return a;
	}
	public void setA(Map<String, Object> a) {
		this.a = a;
	}
	
}
