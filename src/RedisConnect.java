import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

public class RedisConnect {
	Jedis jedis;
	public RedisConnect() {
		Connect();
	}
	
	public Boolean Connect() {
		boolean isConnect = false;
		try {
			jedis = new Jedis("172.24.52.3",6379);
			// prints out "Connection Successful" if Java successfully connects to Redis server.
			System.out.println("Connection Successful");
			jedis.connect();
			isConnect = jedis.isConnected();
			System.out.println("isConnect : " + isConnect);
			System.out.println("The server is running " + jedis.ping());
//			jedis.set("company-name", "500Rockets.io");
//			System.out.println("Stored string in redis:: "+ jedis.get("company-name"));
//


		}catch(Exception e) {
			System.out.println(e);
		}
		return isConnect;
	}
	
	public void setCache(String IdCache,String dataCache) {
		try {
			jedis.set(IdCache, dataCache);
			jedis.expire(IdCache, 1800);
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public String getCache(String IdCache) {
		String res = "";
		try {
			String data = jedis.get(IdCache);
			res = data;
			//System.out.println("Stored string in redis:: "+ jedis.get(IdCache));
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return res;
	}
	
//	public static void main(String args[]) {
//		try {
//			RedisConnect con = new RedisConnect();
//			//con.setCache("dataTes","hello World Redis");
//			con.getCache("dataTes");
//		}catch(Exception exc) {
//			exc.printStackTrace();
//		}
//	}
//	
	 
}
