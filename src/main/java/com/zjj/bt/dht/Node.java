package com.zjj.bt.dht;

import java.util.Date;

/**
 *ClassName:Node
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class Node extends AbstractBucket{
	private byte[] nodeId;
	private String ip;
	private int port;
	private Date create_date;
	private Date update_date;
	public byte[] getNodeId() {
		return nodeId;
	}
	public void setNodeId(byte[] nodeId) {
		this.nodeId = nodeId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public boolean check() {
		return false;
	}
}
