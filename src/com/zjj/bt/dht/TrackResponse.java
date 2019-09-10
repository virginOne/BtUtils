package com.zjj.bt.dht;

/**
 *ClassName:TrackResponse
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class TrackResponse {
	private String failureReason;
	private String warningMessage;
	private long interval;
	private long minIterval;
	private String trackId;
	private long complete;
	private long incomplete;
	private Peer peers;
	private byte[] peersCompact;
}
