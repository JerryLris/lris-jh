package com.lris.ain.core.data.redis;

import redis.clients.jedis.Jedis;

public class RedisUtils {

	 private Jedis jedis; 
	 
	 public RedisUtils(String source,int port){
		 jedis = new Jedis(source,port);
	 }
	 
	 public void setVal(String key,String val){
		 jedis.append(key, val);
	 }
	 
	 public String getVal(String key){
		 return jedis.get(key);
	 }
}
