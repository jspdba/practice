package top.wuchaofei.service.impl;

import cn.zhouyafeng.itchat4j.Wechat;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.wuchaofei.service.WechatService;

/**
 * Created by cofco on 2017/6/17.
 */
@Service("basicWechatService")
public class WechatServiceImpl implements WechatService {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Value("${qrPath}")
    private String qrPath;

    @Override
    public void login() {
        Wechat wechat = new Wechat(this, qrPath);
        wechat.start();
    }

    @Override
    public String textMsgHandle(JSONObject msg) {
        logger.info(JSONObject.toJSONString(msg));
        return null;
    }

    @Override
    public String picMsgHandle(JSONObject msg) {
        logger.info(JSONObject.toJSONString(msg));

        return null;
    }

    @Override
    public String voiceMsgHandle(JSONObject msg) {
        logger.info(JSONObject.toJSONString(msg));
        return null;
    }

    @Override
    public String viedoMsgHandle(JSONObject msg) {
        logger.info(JSONObject.toJSONString(msg));
        return null;
    }

    @Override
    public String nameCardMsgHandle(JSONObject msg) {
        logger.info(JSONObject.toJSONString(msg));
        return null;
    }
}
