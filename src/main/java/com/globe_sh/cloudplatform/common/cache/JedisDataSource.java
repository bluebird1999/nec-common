package com.globe_sh.cloudplatform.common.cache;


import com.globe_sh.cloudplatform.common.util.StaticVariable;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDataSource {

	private static JedisDataSource instance;
	
	private JedisPool pool = null;

	private JedisDataSource() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
	    config.setMaxIdle(20);
	    config.setMaxWaitMillis(1000);
	    pool = new JedisPool(config, StaticVariable.REDIS_IP, StaticVariable.REDIS_PORT, 120000);
	}
	
	public static synchronized JedisDataSource getInstance() {
		if(instance == null)
			instance = new JedisDataSource();
		
		return instance;
	}
	
	public Jedis getJedisConnection() {
		
	    return pool.getResource();
	}
	
	@SuppressWarnings("deprecation")
	public void closeJedisConnection(Jedis jedis) {
		
	    pool.returnResource(jedis);
	}
}
