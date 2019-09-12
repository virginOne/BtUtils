package com.zjj.bt.dht.krpc;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjj.bt.bencode.Bencoder;
import com.zjj.bt.exception.DecodeException;
import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:KrpcHandler
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月10日
 *@Version 1.0
 */
public class KrpcHandler {
	
	private static Log log=LogFactory.getLog(KrpcHandler.class);
	
	public void handle(byte[] bytes) {
		String Bstr=new String(bytes,Charset.forName("utf-8"));
		try {
			Map<String, Object> map=Bencoder.getDecoder().decode(Bstr);
			String y=(String) map.get("y");
			if(y==null) {
				log.error(ErrorCode._203);
				return ;
			}
			switch (y) {
			case "q":requestHandler(map);break;
			case "r":responseHandler(map);break;
			case "e":errorHandler(map);break;
			default:log.error(ErrorCode._203);return ;
			}
		} catch (DecodeException e) {
			log.error("The format of the string not belong to Bencoding.The string :"+Bstr+".");
		}
	}
	private void requestHandler(Map<String, Object> map) {
			String q=(String) map.get("q");	
			if(q==null) {
				System.out.println(this.getClass().getName()+"occured Error");
				return ;
			}
			switch (q) {
			case "ping":pingHandler(map);;break;
			case "get_peer":get_peersHandler(map);break;
			case "find_node":fing_nodeHandler(map);break;
			case "announce_peer":announce_peerHandler(map);break;
			default:log.error(ErrorCode._203);
			}
	}
	
	private void responseHandler(Map<String, Object> map) {
		
	}
	@SuppressWarnings("unchecked")
	private void errorHandler(Map<String, Object> map) {
		List<Object> e=(List<Object>) map.get("e");
		if(e==null || e.size()!=2) {
			log.error(ErrorCode._203);
			return ;
		}
		String t=(String) map.get("t");
		if (t==null) {
			return;
		}
		Map<String, KrpcRequest> requestMap=KrpcClient.getRequestMap();
		
		KrpcRequest request=requestMap.get(t);
		if(request!=null) {
			requestMap.remove(t);
		}
		
	}
	private void pingHandler(Map<String, Object> map) {
		
	}
	private void get_peersHandler(Map<String, Object> map) {
		
	}
	private void fing_nodeHandler(Map<String, Object> map) {
		
	}
	private void announce_peerHandler(Map<String, Object> map) {
		
	}
}
