package com.zjj.bt.dht;


/**
 *ClassName:Tracker
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */

public class TrackerRequest{
	public static class Event{
		final static String STARED="stared";
		final static String COMPLETED="completed";
		final static String STOPPED="stoped";
		final static String EMPTY="empty";
	}
    private byte[] info_hash;
    private String peer_id;
    private String ip;
    private int port;
    private long uploaded;
    private long downloaded;
    private long left;
    private String event;
    private boolean compact;
    private boolean no_peer_id;
    private int numWant=50;
    private String key;
    private String trackerId;
	public byte[] getInfo_hash() {
		return info_hash;
	}
	public void setInfo_hash(byte[] info_hash) {
		this.info_hash = info_hash;
	}
	public String getPeer_id() {
		return peer_id;
	}
	public void setPeer_id(String peer_id) {
		this.peer_id = peer_id;
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
	public long getUploaded() {
		return uploaded;
	}
	public void setUploaded(long uploaded) {
		this.uploaded = uploaded;
	}
	public long getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(long downloaded) {
		this.downloaded = downloaded;
	}
	public long getLeft() {
		return left;
	}
	public void setLeft(long left) {
		this.left = left;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public boolean isCompact() {
		return compact;
	}
	public void setCompact(boolean compact) {
		this.compact = compact;
	}
	public boolean isNo_peer_id() {
		return no_peer_id;
	}
	public void setNo_peer_id(boolean no_peer_id) {
		this.no_peer_id = no_peer_id;
	}
	public int getNumWant() {
		return numWant;
	}
	public void setNumWant(int numWant) {
		this.numWant = numWant;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTrackerId() {
		return trackerId;
	}
	public void setTrackerId(String trackerId) {
		this.trackerId = trackerId;
	}
}