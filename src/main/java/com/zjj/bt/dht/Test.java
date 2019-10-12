package com.zjj.bt.dht;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.apache.commons.codec.binary.Hex;

import com.zjj.bt.dht.krpc.KrpcClient;
import com.zjj.bt.utils.BtUtils;

/**
 * ClassName:Test
 * 
 * @Description:
 *
 * @Author zjj
 * @Date 2019年9月6日
 * @Version 1.0
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

		// socket test
//		Socket socket=new Socket("67.215.246.10",)
//		System.out.println(Double.MAX_VALUE+3E200);
//		System.out.println(Double.MAX_VALUE+3E20==Double.MAX_VALUE);
//		System.out.println(Double.MAX_VALUE);
//		String s="ping";
//		switch (s) {
//		case "ping":System.out.println("123");break;
//		default:System.out.println("error");
//			break;
//		}

		String[] BOOTSTRAP_NODES = { "udp://tracker.open-internet.nl:6969/announce",
				"udp://tracker.coppersurfer.tk:6969/announce", "udp://exodus.desync.com:6969/announce",
				"udp://tracker.opentrackr.org:1337/announce", "udp://tracker.internetwarriors.net:1337/announce",
				"udp://9.rarbg.to:2710/announce", "udp://public.popcorn-tracker.org:6969/announce",
				"udp://tracker.vanitycore.co:6969/announce", "https://1.track.ga:443/announce",
				"udp://tracker.tiny-vps.com:6969/announce", "udp://tracker.cypherpunks.ru:6969/announce",
				"udp://thetracker.org:80/announce", "udp://tracker.torrent.eu.org:451/announce",
				"udp://retracker.lanta-net.ru:2710/announce", "udp://bt.xxx-tracker.com:2710/announce",
				"http://retracker.telecom.by:80/announce", "http://retracker.mgts.by:80/announce",
				"http://0d.kebhana.mx:443/announce", "udp://torr.ws:2710/announce", "udp://open.stealth.si:80/announce",
				"router.bittorrent.com:6881", "dht.transmissionbt.com:6881", "router.utorrent.com:6881" };
		new Thread(()->{
			try {
				KrpcClient client=new KrpcClient();
				Node node=new Node();
				node.setNodeId(BtUtils.getOnlyNodeId());
				String find_str=client.find_node(node, node);
				
				DatagramSocket upd=new DatagramSocket(8090);
				DatagramPacket p=new DatagramPacket(find_str.getBytes(), find_str.getBytes().length);
				p.setSocketAddress(new InetSocketAddress("router.bittorrent.com", 6881));
				System.out.println(find_str);
				System.out.println(p.getAddress().toString());
				System.out.println(p.getPort());
				upd.send(p);
				DatagramPacket r=new DatagramPacket(new byte[10240], 10240);
				upd.receive(r);
				System.out.println(new String(r.getData()));
			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;
		new Thread(()->{
			try {
				KrpcClient client=new KrpcClient();
				Node node=new Node();
				node.setNodeId(BtUtils.getOnlyNodeId());
				String find_str=client.find_node(node, node);
				
				DatagramSocket upd=new DatagramSocket(8091);
				DatagramPacket p=new DatagramPacket(find_str.getBytes(), find_str.getBytes().length);
				p.setSocketAddress(new InetSocketAddress("dht.transmissionbt.com", 6881));
				System.out.println(find_str);
				System.out.println(p.getAddress().toString());
				System.out.println(p.getPort());
				upd.send(p);
				DatagramPacket r=new DatagramPacket(new byte[10240], 10240);
				upd.receive(r);
				System.out.println(new String(r.getData()));
			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;
		new Thread(()->{
			try {
				KrpcClient client=new KrpcClient();
				Node node=new Node();
				node.setNodeId(BtUtils.getOnlyNodeId());
				String find_str=client.find_node(node, node);
				
				DatagramSocket upd=new DatagramSocket(8092);
				DatagramPacket p=new DatagramPacket(find_str.getBytes(), find_str.getBytes().length);
				p.setSocketAddress(new InetSocketAddress("router.utorrent.com", 6881));
				System.out.println(find_str);
				System.out.println(p.getAddress().toString());
				System.out.println(p.getPort());
				upd.send(p);
				DatagramPacket r=new DatagramPacket(new byte[10240], 10240);
				upd.receive(r);
				System.out.println(new String(r.getData()));
			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;
		
//		new Thread(()->{
//			try {
//				DatagramSocket upd=new DatagramSocket(9091);
//				DatagramPacket p=new DatagramPacket(new byte[10240], 10240);
//				while(true) {
//					upd.receive(p);
//					System.out.println(new String(p.getData(),"utf-8"));
//				}
//			} catch (SocketException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}).start();
	}
}
