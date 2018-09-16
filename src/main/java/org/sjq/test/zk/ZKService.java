package org.sjq.test.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

@Component
public class ZKService {
	
	private ZooKeeper zookeeper;
	
	public synchronized void init() throws IOException {
		if(zookeeper ==null) {
			zookeeper=new ZooKeeper("127.0.0.1:2181", 30000, null);
		}
	}
	/**
	 * 创建节点
	 * @param node
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	public String createNode(String node,String data) throws Exception {
		if(this.zookeeper==null) {
			init();
		}
		return zookeeper.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	/**
	 * @param node
	 * @return
	 */
	public boolean isNodeExists(String node)throws Exception {
		Stat stat=getNodeStat(node);
		if(stat==null) {
			return false;
		}
		return true;
	}
	/**
	 * 获取stat对象
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public Stat getNodeStat(String node) throws Exception{
		if(this.zookeeper==null) {
			init();
		}
		return zookeeper.exists(node, false);
	}
	
	/**
	 * 获取节点上设置的数据
	 * @param node
	 * @return
	 */
	public String getNodeDate(String node) throws Exception{
		Stat stat=getNodeStat(node);
		if(null!=stat) {
			byte[] result=zookeeper.getData(node, false, stat);
			return new String(result);
		}
		return null;
	}
}
