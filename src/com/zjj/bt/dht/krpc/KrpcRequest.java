package com.zjj.bt.dht.krpc;

import java.util.Map;

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
	
	KrpcRequest(byte[] bytes) throws DecodeException{
		Map<String, Object> map=Bencoder.getDecoder().decode(new String(bytes));
	}
}
