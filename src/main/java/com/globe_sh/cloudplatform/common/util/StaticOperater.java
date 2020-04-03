package com.globe_sh.cloudplatform.common.util;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;

import com.globe_sh.cloudplatform.common.cache.JedisOperater;

public class StaticOperater {

	private static final String ROWKEY_QUALIFIER = "-";
	
	public static String getRowKey(String vehicleId, long time) {
		
		return vehicleId + ROWKEY_QUALIFIER + time;
	}
	
	public static String getRowKey(String vehicleId, String time) {
		Timestamp ts = StaticMethod.getTimestamp(time);
		if(ts == null)
			return null;
		
		long ltime = ts.getTime();
		
		return getRowKey(vehicleId, ltime);
	}
	
	public static String getIdleAgent() {
		Map<String, String> map = JedisOperater.getAgentConnectionMap();
		int min = Integer.MAX_VALUE;
		String idleIp = StaticOperater.getLocalIp();
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			int value = Integer.parseInt(entry.getValue());
			if(value < min) {
				min = value;
				idleIp = key;
			}
		}
		
		return idleIp;
	}
	
	public static String getLocalIp() {
		String ip = StaticVariable.LOCAL_IP; 
		
		return ip;
	}
}
