package top.wuchaofei.service.impl;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.wuchaofei.service.WechatService;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cofco on 2017/6/18.
 */
@Service("tulingWechatServiceImpl")
public class TulingWechatServiceImpl implements WechatService {
    static final MyHttpClient myHttpClient = Core.getInstance().getMyHttpClient();

    Logger logger= LoggerFactory.getLogger(getClass());
    @Value("${qrPath}")
    private String qrPath;

    @Value("${apiKey}")
    private String apiKey;

    @Value("${tulingApi}")
    private String tulingApi;

    @Value("${voicePath}")
    private String voicePath1;

    @Value("${videoPath}")
    private String videoPath1;

    @Value("${tuling.username}")
    private String username;

    @Value("${tuling.password}")
    private String password;



    @Override
    public void login() {
        Wechat wechat = new Wechat(this, qrPath);
        wechat.start();
    }

    @Override
    public String textMsgHandle(JSONObject msg) {
        String result = "";
        String text = msg.getString("Text");
        String url = tulingApi;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key", apiKey);
        paramMap.put("info", text);
        paramMap.put(username, password);
        String paramStr = JSON.toJSONString(paramMap);
        try {
            HttpEntity entity = myHttpClient.doPost(url, paramStr);
            result = EntityUtils.toString(entity, "UTF-8");
            JSONObject obj = JSON.parseObject(result);
            if (obj.getString("code").equals("100000")) {
                result = obj.getString("text");
            } else {
                result = "处理有误";
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    @Override
    public String picMsgHandle(JSONObject msg) {
        return "收到图片";
    }

    @Override
    public String voiceMsgHandle(JSONObject msg) {
        String fileName = String.valueOf(new Date().getTime());
        String voicePath = voicePath1 + File.separator + fileName + ".mp3";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
        return "收到语音";
    }

    @Override
    public String viedoMsgHandle(JSONObject msg) {
        String fileName = String.valueOf(new Date().getTime());
        String viedoPath = videoPath1 + File.separator + fileName + ".mp4";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
        return "收到视频";
    }


    @Override
    public String nameCardMsgHandle(JSONObject msg) {
        // TODO Auto-generated method stub
        return null;
    }
}
