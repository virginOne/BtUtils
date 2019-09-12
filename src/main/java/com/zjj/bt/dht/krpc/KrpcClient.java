package com.zjj.bt.dht.krpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;


import com.zjj.bt.bencode.Bencoder;
import com.zjj.bt.dht.Node;
import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:KrpcClient
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月10日
 *@Version 1.0
 */
public class KrpcClient {
	private static AtomicInteger atomicInteger;
	private static List<Token> tokens;
	private static Map<String, KrpcRequest> requestMap;
	static {
		atomicInteger=new AtomicInteger(0);
		tokens=new ArrayList<Token>();
		requestMap=new HashMap<String, KrpcRequest>();
	}
	private static boolean token_lock=false;
	
	public static List<Token> getTokens() {
		return tokens;
	}
	
	public static Map<String, KrpcRequest> getRequestMap(){
		return requestMap;
	}
	
	public String ping(Node node) {
		Map<String,Object> ping=new TreeMap<>();
		
		checkId();
		String id=Integer.toHexString(atomicInteger.getAndIncrement());
		ping.put("t", id);
		ping.put("y", "q");
		ping.put("q", "ping");
		Map<String,Object> a=new TreeMap<>();
		a.put("id", BtUtils.toHexString(node.getNodeId()));
		ping.put("a",a);
		
		String message=Bencoder.geEncoder().encode(ping);
		return message;
	}
	public String find_node(Node node,Node target) {
		Map<String,Object> find_node=new TreeMap<String, Object>();
		
		
		checkId();
		String id=Integer.toHexString(atomicInteger.getAndIncrement());
		find_node.put("t", id);
		find_node.put("y","q");
		find_node.put("q", "find_node");
		Map<String,Object> a=new TreeMap<>();
		a.put("id", BtUtils.toHexString(node.getNodeId()));
		a.put("target", BtUtils.toHexString(target.getNodeId()));
		find_node.put("a", a);
		
		String message=Bencoder.geEncoder().encode(find_node);
		return message;
	}
	public String get_peers(Node node,byte[] info_hash) {
		Map<String,Object> get_peers=new TreeMap<String, Object>();
		
		checkId();
		String id=Integer.toHexString(atomicInteger.getAndIncrement());
		get_peers.put("t", id);
		get_peers.put("y","q");
		get_peers.put("q", "get_peers");
		Map<String,Object> a=new TreeMap<>();
		a.put("id", BtUtils.toHexString(node.getNodeId()));
		a.put("info_hash", BtUtils.toHexString(info_hash));
		String message=Bencoder.geEncoder().encode(get_peers);
		get_peers.put("a", a);
		return message;
	}
	
	public String announce_peers(Node node,byte[] info_hash) {
		return announce_peers(node,info_hash,true);
	}
	
	public String announce_peers(Node node,byte[] info_hash,boolean implied_port) {
		
		Map<String,Object> announce_peers=new TreeMap<String, Object>();
		
		
		checkId();
		String id=Integer.toHexString(atomicInteger.getAndIncrement());
		announce_peers.put("t", id);
		announce_peers.put("y","q");
		announce_peers.put("q", "announce_peers");
		Map<String,Object> a=new TreeMap<>();
		a.put("id", BtUtils.toHexString(node.getNodeId()));
		a.put("implied_port", implied_port);
		a.put("info_hash", BtUtils.toHexString(info_hash));
		if(!implied_port)
			a.put("port", "6881");
		a.put("token", "asdf");
		
		announce_peers.put("a", a);
		String message=Bencoder.geEncoder().encode(announce_peers);
		return message;
	}
	
	
	private void checkId() {
		atomicInteger.compareAndSet(65535, 0);//2^16
	}
	private void chekcToken() {
		if(!token_lock) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					long valid;
					Token token;
					for(int i=0;i<tokens.size();i++) {
						token=tokens.get(i);
						if(token==null || (valid=token.getInvalid_data())<1 || valid<System.currentTimeMillis()) {
							tokens.remove(i);
						}
					}
					token_lock=false;
				}
			}).start();;
		}
	}
}
