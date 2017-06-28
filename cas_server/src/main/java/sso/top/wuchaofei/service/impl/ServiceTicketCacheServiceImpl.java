package sso.top.wuchaofei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sso.top.wuchaofei.service.ServiceTicketCacheService;

/**
 *
 * Created by wuchaofei on 2017/4/6.
 */
@Service("serviceTicketCacheServiceImpl")
public class ServiceTicketCacheServiceImpl extends CacheServiceAdaper implements ServiceTicketCacheService {

    @Autowired
    RedisTemplate redisTemplate;

    private static final String PREFFIX_SERVICE_TOKEN="SERVICE_TOKEN_";

    @Override
    public void setEx(String key, String value) {
        key=PREFFIX_SERVICE_TOKEN+key;
        super.setEx(key,value);
    }

    @Override
    public void set(String key, String value, long timeout) {
        super.set(key,value,timeout);
    }

    @Override
    public Object get(String key) {
        key=PREFFIX_SERVICE_TOKEN+key;
        return super.get(key);
    }

    @Override
    public void del(String key) {
        key=PREFFIX_SERVICE_TOKEN+key;
        super.del(key);
    }

    @Override
    public boolean ismember(String key) {
        key=PREFFIX_SERVICE_TOKEN+key;
        return super.ismember(key);
    }

    @Override
    public boolean isExpired(String key) {
        key=PREFFIX_SERVICE_TOKEN+key;
        return super.isExpired(key);
    }
}
