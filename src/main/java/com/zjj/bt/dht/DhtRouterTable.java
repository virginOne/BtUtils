package com.zjj.bt.dht;

import java.util.ArrayList;
import java.util.List;

import com.zjj.bt.utils.BtUtils;

/**
 *ClassName:DhtRouterTable
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月6日
 *@Version 1.0
 */
public class DhtRouterTable {
	//用SHA1算法加密 根据kademlia算法可以分成160个K-Bucket
	private final int size=160;
	private byte[] nodeId;
	private List<Bucket<Node>> table=new ArrayList<Bucket<Node>>(160);
	/**
	 * Build DHTRouter table according to yourself nodeId.
	 */
	public DhtRouterTable(byte[] nodeId) {
		for(int i=0;i<size;i++) {
			table.add(null);
		}
		this.nodeId=nodeId;
	}
	
	/**
	 * Add a new node to the correct position(correct K-Bucket).
	 */
	public void addNode(Node node) {
		int index=IndexOf(node);
		Bucket<Node> bucket=table.get(index);
		if(bucket==null) {
			bucket=new Bucket<>();
		}
		bucket.add(node);
	}
	

	/**
	 * Return the K-Bucket index of node.
	 */
	private int IndexOf(Node node) {
		byte[] distance=BtUtils.XOR(nodeId, node.getNodeId());
		
		//get the K-Bucket index of new node
		//the index is the highest bit of '1'
		String hexString=BtUtils.toHexString(distance).toUpperCase();
		char[] chars=hexString.toCharArray();
		int len=chars.length;
		for(int i=0;i<len;i++) {
			if(chars[i]!='0') {
				int t=len-i-1;
				int t2;
				int num=BtUtils.numOfHexChar(chars[i]);
				if(num<2) {
					t2=1;
				}else if(num<4) {
					t2=2;
				}else if(num<8) {
					t2=3;
				}else {
					t2=4;
				}
				return t*4+t2-1;
			}
		}
		return -1;
	}
	
	/**
	 * return the size of DHTRouter table.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * return the NodeId of the DHTRouter.
	 */
	public byte[] getNodeId() {
		return nodeId;
	}
}
