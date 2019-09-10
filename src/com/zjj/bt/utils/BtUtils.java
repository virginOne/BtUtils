package com.zjj.bt.utils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.spi.CharsetProvider;
import java.util.BitSet;
import java.util.Random;

import com.alibaba.fastjson.util.UTF8Decoder;
import com.zjj.bt.exception.HexConvertException;

/**
 *ClassName:Utils
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class BtUtils {
	/**
	 * Hex String
	 * */
	private final static char[] HexChars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	private final static byte[] OnlyNodeId= {0x05,0x0a,0x0f,0x05,0x08,0x7a,0x5a,0x7f,0x5d,0x70,
									-0x2a,0x55,0x12,0x78,0x68,0x67,0x13,0x67,0x78,0x4f};
	
	public static String castToString(Object value) {
		if(value==null) {
			return null;
		}
		return value.toString();
	}
	/**
	 * get 160 bit ID
	 * 160 bits=20bytes
	 */
	public static byte[] getNodeId() {
		byte[] bytes=new byte[20];
		Random random=new Random();
		random.nextBytes(bytes);
		return bytes;
	}
	public static byte[] UTF8decode(String utf8) {
		return utf8.getBytes(Charset.forName("UTF-8"));
	}
	public static String UTF8encode(byte[] bytes) {
		return new String(bytes,Charset.forName("UTF-8"));
	}
	public static int numOfHexChar(char hex) {
		for(int i=0;i<HexChars.length;i++) {
			if(HexChars[i]==hex) 
				return i;
		}
		return -1;
	}
	
	public static byte[] getOnlyNodeId() {
		return OnlyNodeId;
	}
	
	public static String toHexString(byte[] data) {
		int len=data.length;
		if(data==null || len<1) {
			return null;
		}
		char[] chars=new char[len*2];
		for(int i=0;i<data.length;i++) {
			chars[i*2]=HexChars[(data[i]>>>4)&0x0f];//移位 清除低四位 获取高四位
			chars[i*2+1]=HexChars[data[i]&0x0f];//清除高四位 获取低四位
		}
		return new String(chars);
	}
	public static double toDouble(byte[] data) {
		char[] chars=toHexString(data).toCharArray();
		double d=0;
		double t=1;
		int j;
		char c;
		int len=chars.length;
		for(int i=len-1;i>=0;i--) {
			c=chars[i];
			for(j=0;j<HexChars.length;j++) {
				if(c==HexChars[j])
					break;
			}
			d+=j*t;
			t*=16;
		}
		return d;
	}
	
	public static byte[] XOR(byte[] node1,byte[] node2) {
		byte[] distance=new byte[20];
		for(int i=0;i<distance.length;i++) {
			distance[i]=(byte) (node1[i]^node2[i]);
		}
		return distance;
	}
}
