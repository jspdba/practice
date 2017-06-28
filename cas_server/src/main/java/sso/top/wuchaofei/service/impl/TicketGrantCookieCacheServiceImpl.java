package sso.top.wuchaofei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sso.top.wuchaofei.service.TicketGrantCookieCacheService;

/**
 *
 * Created by wuchaofei on 2017/4/6.
 */
@Service
public class TicketGrantCookieCacheServiceImpl extends CacheServiceAdaper implements TicketGrantCookieCacheService {

    @Autowired
    RedisTemplate redisTemplate;

    private static final String PREFFIX_TICKET_GRANT_COOKIE="TICKET_GRANT_COOKIE_";

    @Override
    public void setEx(String key, String value) {
        key=PREFFIX_TICKET_GRANT_COOKIE+key;
        super.setEx(key,value);
    }

    @Override
    public boolean ismember(String key) {
        key=PREFFIX_TICKET_GRANT_COOKIE+key;
        return super.ismember(key);
    }

    @Override
    public void set(String key, String value, long timeout) {
        super.set(key,value,timeout);
    }

    @Override
    public Object get(String key) {
        key=PREFFIX_TICKET_GRANT_COOKIE+key;
        return super.get(key);
    }

    @Override
    public void del(String key) {
        key=PREFFIX_TICKET_GRANT_COOKIE+key;
        super.del(key);
    }

    @Override
    public boolean isExpired(String key) {
        key=PREFFIX_TICKET_GRANT_COOKIE+key;
        return super.isExpired(key);
    }
}
