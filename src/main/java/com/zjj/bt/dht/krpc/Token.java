package com.zjj.bt.dht.krpc;

/**
 *ClassName:Token
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月12日
 *@Version 1.0
 */
public class Token {
	private String token;
	private long create_date;
	private long invalid_data;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getCreate_date() {
		return create_date;
	}
	public void setCreate_date(long create_date) {
		this.create_date = create_date;
	}
	public long getInvalid_data() {
		return invalid_data;
	}
	public void setInvalid_data(long invalid_data) {
		this.invalid_data = invalid_data;
	}
}
