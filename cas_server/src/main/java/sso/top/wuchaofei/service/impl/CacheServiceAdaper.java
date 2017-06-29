package sso.top.wuchaofei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sso.top.wuchaofei.service.CacheService;
import sso.top.wuchaofei.service.ServiceTicketCacheService;

import java.util.concurrent.TimeUnit;

/**
 * Created by wuchaofei on 2017/4/6.
 */
@Component
public class CacheServiceAdaper implements CacheService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 默认有效时间为5分钟
     * @param key
     * @param value
     */
    @Override
    public void setEx(String key, String value) {
        set(key, value, 1000*60*5);
    }

    @Override
    public void set(String key, String value, long timeout){
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean ismember(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean isExpired(String key) {
        boolean haskey = redisTemplate.hasKey(key);
        if(!haskey){
            return true;
        }
        return redisTemplate.getExpire(key)<0;
    }
}
