package com.zjj.bt.dht;

import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.apache.commons.codec.binary.Hex;

import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:Test
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class Test {

	public static void main(String[] args) {
//		File file = new File("C:\\Users\\0\\Desktop\\神话.torrent");
//		InputStreamReader isr=new InputStreamReader(new FileInputStream(file), "utf-8");
//		BufferedReader br=new BufferedReader(isr);
//		String line;
//		System.out.println(br.readLine());
//		while((line=br.readLine())!=null) {
//			System.out.println(line);
//		}
//		byte[] bytes=BtUtils.getOnlyNodeId();
//		byte[] bytes1 = new byte[20];
//		System.arraycopy(bytes, 0, bytes1, 0, 20);
//		bytes1[19]=0x50;
//		System.out.println(BtUtils.toHexString(bytes));
//		System.out.println(BtUtils.toHexString(bytes1));
//		System.out.println(BtUtils.toHexString(BtUtils.XOR(bytes, bytes1)));
		
//		System.out.println(int.class.getClass() instanceof Integer.class.getClass());
		
		//socket test
//		Socket socket=new Socket("67.215.246.10",)
//		System.out.println(Double.MAX_VALUE+3E200);
//		System.out.println(Double.MAX_VALUE+3E20==Double.MAX_VALUE);
//		System.out.println(Double.MAX_VALUE);
		String s="ping";
		switch (s) {
		case "ping":System.out.println("123");break;
		default:System.out.println("error");
			break;
		}
	}

}
