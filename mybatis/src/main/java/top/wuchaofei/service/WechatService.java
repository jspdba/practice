package top.wuchaofei.service;

import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import org.springframework.stereotype.Service;

/**
 * Created by cofco on 2017/6/17.
 */
@Service
public interface WechatService extends IMsgHandlerFace {
    public void login();
}
