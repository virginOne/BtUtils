package com.zjj.bt.dht.krpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zjj.bt.bencode.Bencoder;
import com.zjj.bt.exception.DecodeException;
import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:KRPC
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月9日
 *@Version 1.0
 */
public class KrpcRequest extends Krpc{
	
	public String error(ErrorCode errorCode) {
		Map<String,Object> error=new TreeMap<String, Object>();
		
		error.put("t", this.getT());
		error.put("y", "e");
		List<Object> e=new ArrayList<Object>();
		e.add(errorCode.getCode());
		e.add(errorCode.getMsg());
		error.put("e", e);
		String message=Bencoder.geEncoder().encode(error);
		return message;
	}
	
	KrpcRequest(byte[] bytes) throws DecodeException{
		Map<String, Object> map=Bencoder.getDecoder().decode(new String(bytes));
	}
	
}
