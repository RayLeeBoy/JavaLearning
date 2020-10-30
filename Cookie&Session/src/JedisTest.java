import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

public class JedisTest {
    @Test
    public void test1() {
        // 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        // 操作
        jedis.set("username", "zhangsan");

        // 关闭连接
        jedis.close();
    }

    @Test
    public void test2() {
        // hset hget hgetall
        // 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        // 操作
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "lisi");
        map.put("age", "18");
        jedis.hset("user", map);

        String username = jedis.hget("user", "username");
        System.out.println(username);

        // 关闭连接
        jedis.close();
    }

    @Test
    public void test3() {
        // hset hget hgetall
        // 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.lpush("arr", "aaa");
        jedis.lpush("arr", "bbb");
        jedis.rpush("arr", "ccc");
        jedis.rpush("arr", "ddd");
//        String arr = jedis.lpop("arr");
        System.out.println(jedis.lpop("arr"));
        System.out.println(jedis.rpop("arr"));
        // 关闭连接
        jedis.close();
    }

    @Test
    public void testJedisPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        JedisPool pool = new JedisPool(config, "localhost", 6379);

        Jedis jedis = pool.getResource();

        jedis.set("hi", "hello");

        String hi = jedis.get("hi");
        
        System.out.println(hi);

        jedis.close();
    }

    @Test
    public void testJedisPoolUntils() {

        Jedis jedis = JedisPoolUtils.getJedis();

        jedis.set("hi", "hello");

        HashMap<String, String> map = new HashMap<>();
        map.put("username", "lisi");
        jedis.hset("user", map);

        String hi = jedis.get("hi");

        System.out.println(hi);

        String s = jedis.hget("user", "username");
        System.out.println(s);

        jedis.close();
    }
}
