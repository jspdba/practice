package sso.top.wuchaofei.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service
public interface CacheService {
    /**
     * 默认设置30天缓存
     * @param key
     * @param value
     */
    public void setEx(String key, String value);
    public void set(String key, String value, long timeout);
    public boolean ismember(String key);
    public Object get(String key);
    public void del(String key);
    public boolean isExpired(String key);
}
