package com.globe_sh.cloudplatform.common.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.globe_sh.cloudplatform.common.util.StaticVariable;

import redis.clients.jedis.Jedis;

public class JedisOperater {
	
	public static Map<String, String> getAgentConnectionMap() {
		Map<String, String> map = new HashMap<String, String>();
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_AGENT_LIST);
		Set<String> keys = jedis.keys("*");
		for(String key : keys) {
			map.put(key, jedis.get(key));
		}
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return map;
	}
	
	public static void updateAgentConnection(String agentIp, int num) {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_AGENT_LIST);
		jedis.set(agentIp, String.valueOf(num));
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	
	/************ Status  Start*************/
	public static Map<String, String> initOnlineStatus() {
		Map<String, String> map = new HashMap<String, String>();
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_ONLINE);
		Set<String> keys = jedis.keys("*");
		for(String key : keys) {
			map.put(key, jedis.get(key));
		}
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return map;
	}
	
	public static void updateOnlineStatus(String vin, String status) {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_ONLINE);
		jedis.set(vin, status);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	
	public static String getOnlineStatus(String sid) {
		String status = null;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_ONLINE);
		status = jedis.get(sid);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return status;
	}
	/************ Status  End*************/
	
	

	/************ Station  Start*************/
	public static boolean validableStation(String sid) {
		boolean valid = false;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_LIST);
		valid = jedis.exists(sid);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return valid;
	}
	public static String getStation(String sid) {
		String stationJson = null;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_LIST);
		stationJson = jedis.get(sid);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return stationJson;
	}
	public static void updateStationStatus(String sid, String stationStatus) {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_STATUS);
		jedis.set(sid, stationStatus);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	/************ Station End *************/
	
	

	/************ Decoder Start *************/
	public static List<String> getDataDecoder(String dataBlock) {
		List<String> dataDecoderList = null;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_PROTOCOL);
		dataDecoderList = jedis.lrange(dataBlock, 0, -1);
		
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		return dataDecoderList;
	}
	public static void addDataDecoder(String dataBlock, String decoder) {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_PROTOCOL);
		jedis.rpush(dataBlock, decoder);
		
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	public static void cleanDeviceProtocol() {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_STATION_PROTOCOL);
		jedis.flushDB();
		
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	/************ Decoder End *************/
	
	
	public static String getNodeInfo(String node) {
		String nodeJson = null;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_AGENT_CLUSTER);
		nodeJson = jedis.get(node);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return nodeJson;
	}
	
	public static void setNodeInfo(String node, String info) {
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_AGENT_CLUSTER);
		jedis.setex(node, 300, info);
		JedisDataSource.getInstance().closeJedisConnection(jedis);
	}
	
	public static boolean hasNode(String node) {
		boolean exist = false;
		Jedis jedis = JedisDataSource.getInstance().getJedisConnection();
		jedis.select(StaticVariable.REDIS_TABLE_AGENT_CLUSTER);
		if(jedis.exists(node)) {
			exist = true;
		}
		JedisDataSource.getInstance().closeJedisConnection(jedis);
		
		return exist;
	}
}
