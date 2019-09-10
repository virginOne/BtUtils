package com.zjj.bt.bencode;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zjj.bt.exception.DecodeException;

/**
 *ClassName:Bencoder
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class Bencoder {
	
	
	public static Decoder getDecoder() {
		return Decoder.DECODER;
	}
	public static Encoder geEncoder() {
		return Encoder.ENCODER;
	}
	
	public static class Decoder{
		final static Decoder DECODER=new Decoder();
		public Map<String,Object> decode(String src) throws DecodeException {
			ByteBuffer bb=ByteBuffer.wrap(src.getBytes());
			Map<String, Object> result=new TreeMap<>();
			if((char)bb.get()=='d') {
				dictionaryAnalysis(result, bb);
			}else {
				throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.");
			}
			return result;
		}
		private void dictionaryAnalysis(Map<String,Object> map, ByteBuffer bb) throws DecodeException{
			char c;
			StringBuilder key=new StringBuilder();
			StringBuilder numberOp=new StringBuilder();
			StringBuilder strTemp=new StringBuilder();
			while(bb.hasRemaining()){
				c=(char)bb.get();
				switch(c){
					case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':{
						numberOp.append(c);
					};break;
					case 'd':{
						if(key.length()>0){
							Map<String, Object> dic=new TreeMap<String,Object>();
							map.put(key.toString(),dic);
							key.delete(0,key.length());
							dictionaryAnalysis(dic, bb);
						}else{
							throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.");
						}
					};break;
					case 'l':{
						if(key.length()>0){
							List<Object> list=new ArrayList<Object>();
							map.put(key.toString(),list);
							key.delete(0, key.length());
							listAnalysis(list, bb);
						}else{
							throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.");
						}
					};break;
					case ':':{
						if(key.length()>0 && numberOp.length()>0){
							int length=Integer.parseInt(numberOp.toString());
							byte[] b=new byte[length];
							for(int i=0;i<length;i++){
								b[i]=bb.get();
							}
							strTemp.append(new String(b));
							map.put(key.toString(),strTemp.toString());
							key.delete(0,key.length());
							strTemp.delete(0,strTemp.length());
							numberOp.delete(0,numberOp.length());
						}else if(numberOp.length()>0){
							for(int i=0;i<Integer.parseInt(numberOp.toString());i++){
								strTemp.append((char)bb.get());
							}
							key.append(strTemp.toString());
							strTemp.delete(0,strTemp.length());
							numberOp.delete(0,numberOp.length());
						}else{
							throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.");
						}
					};break;
					case 'i':{
						if(key.length()>0){
							char cTemp;
							while((cTemp=(char)bb.get())!='e'){
								strTemp.append(cTemp);
							}
							if(!strTemp.toString().matches("\\d+")){
								throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.The charactor of the position should be a number.");
							}else{
								map.put(key.toString(),Integer.parseInt(strTemp.toString()));
								strTemp.delete(0,strTemp.length());
								key.delete(0,key.length());
							}
						}else{
							throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.");
						}
					};break;
					case 'e':return;
				}
			}
		}
		private void listAnalysis(List<Object> list, ByteBuffer bb) throws DecodeException{
			char c;
			StringBuilder numberOp=new StringBuilder();
			StringBuilder strTemp=new StringBuilder();
			while(bb.hasRemaining()){
				c=(char)bb.get();
				switch(c){
					case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':{
						numberOp.append(c);
					};break;
					case 'd':{
						Map<String, Object> dic=new TreeMap<String,Object>();
						list.add(dic);
						dictionaryAnalysis(dic, bb);
					};break;
					case 'l':{
						List<Object> newList=new ArrayList<Object>();
						list.add(newList);
						listAnalysis(newList, bb);
					};break;
					case ':':{
						if(numberOp.length()>0){
							int length=Integer.parseInt(numberOp.toString());
							byte[] b=new byte[length];
							for(int i=0;i<length;i++){
								b[i]=bb.get();
							}
							strTemp.append(new String(b));
							list.add(strTemp.toString());
							strTemp.delete(0,strTemp.length());
							numberOp.delete(0,numberOp.length());
						}
					};break;
					case 'i':{
						char cTemp;
						while((cTemp=(char)bb.get())!='e'){
							strTemp.append(cTemp);
						}
						if(!strTemp.toString().matches("\\d+")){
							throw new DecodeException("The form of becode is wrong.Error occurred in "+bb.position()+"byte.The charactor of the position should be a number.");
						}else{
							list.add(Integer.parseInt(strTemp.toString()));
							strTemp.delete(0,strTemp.length());;
						}
					};break;
					case 'e':return;
				}
			}
		}

	}
	public static class Encoder{
		final static Encoder ENCODER=new Encoder();
		@SuppressWarnings("unchecked")
		public String encode(Object obj) {
			String result="";
			if(obj==null) {
				return null;
			}else if(obj instanceof String) {
				String str=obj.toString();
				if(str==null || str.length()<1) {
					return null;
				}
				result=String.valueOf(str.length())+":"+str;
			}else if(obj instanceof Integer) {
				String str=((Integer)obj).toString();
				result="i"+str+"e";
			}else if(obj instanceof Long) {
				String str=((Long)obj).toString();
				result="i"+str+"e";
			}else if(obj instanceof Collection){
				@SuppressWarnings("unchecked")
				Collection<Object> collection=(Collection<Object>)obj;
				if(collection.size()<1) {
					return null;
				}
				result+="l";
				String t;
				for(Object o :collection) {
					t=encode(o);
					result+=t==null?"":encode(o);
				}
				result+="e";
			}else if(obj instanceof Map) {

	            Map<String, Object> map = (Map<String, Object>) obj;

	            if(map.size()<1 || map.isEmpty()) {
	            	return null;
	            }
	            result+="d";
	            String t;
	            for (Map.Entry<String, Object> entry : map.entrySet()) {
	            	t=encode(entry.getValue());
	            	if(t!=null) {
	            		result+=encode(entry.getKey());
	            		result+=":";
	            		result+=t;
	            	}
	            }
	            result+="e";
			}
			return result;
		}
		
	}
}
