package top.wuchaofei.service;

import org.springframework.stereotype.Service;

/**
 * Created by cofco on 2017/6/17.
 */
@Service
public interface WechatService{
    void login();

    /**
     * 获取登录状态 0:已登录，-1：未登录
     *
     * @return
     */
    int status();

    void logout();
}
