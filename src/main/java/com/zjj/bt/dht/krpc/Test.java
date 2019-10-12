package com.zjj.bt.dht.krpc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Base64;

import com.zjj.bt.dht.Node;
import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:Test
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月16日
 *@Version 1.0
 */
public class Test {
	
	public static void main(String[] args) throws SocketException {
		DatagramSocket socketServer=new DatagramSocket(8989);
		
		KrpcClient client=new KrpcClient();
		Node node=new Node();
		node.setIp("10.142.12.131");
		node.setPort(8989);
		node.setNodeId(BtUtils.getOnlyNodeId());
		String msg=client.find_node(node,node);
		DatagramPacket p=new DatagramPacket(msg.getBytes(), msg.getBytes().length,new InetSocketAddress("87.98.162.88", 6881));
		try {
			socketServer.send(p);
			while(true) {
				socketServer.receive(p);
				System.out.println(p.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
