package top.wuchaofei.service.impl;

import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.core.MsgCenter;
import cn.zhouyafeng.itchat4j.service.ILoginService;
import cn.zhouyafeng.itchat4j.thread.CheckLoginStatusThread;
import cn.zhouyafeng.itchat4j.utils.SleepUtils;
import cn.zhouyafeng.itchat4j.utils.tools.CommonTools;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.wuchaofei.handler.WechatSimpleHandler;
import top.wuchaofei.service.WechatService;

/**
 * Created by cofco on 2017/6/17.
 */
@Service("basicWechatService")
public class WechatServiceImpl implements WechatService {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Value("${qrPath}")
    private String qrPath;
    private static Core core = Core.getInstance();

    @Autowired
    WechatSimpleHandler wechatSimpleHandler;

    @Override
    public void login() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (core.isAlive()) { // 已登陆
                    logger.info("itchat4j已登陆");
                    return;
                }

                System.setProperty("jsse.enableSNIExtension", "false");
                ILoginService loginService = new LoginServiceImpl();
                while (true) {
                    for (int count = 0; count < 10; count++) {
                        logger.info("获取UUID");
                        while (loginService.getUuid() == null) {
                            logger.info("1. 获取微信UUID");
                            while (loginService.getUuid() == null) {
                                logger.warn("1.1. 获取微信UUID失败，两秒后重新获取");
                                SleepUtils.sleep(2000);
                            }
                        }
                        logger.info("2. 获取登陆二维码图片");
                        if (loginService.getQR(qrPath)) {
                            break;
                        } else if (count == 10) {
                            logger.error("2.2. 获取登陆二维码图片失败，系统退出");
                            System.exit(0);
                        }
                    }
                    logger.info("3. 请扫描二维码图片，并在手机上确认");
                    if (!core.isAlive()) {
                        loginService.login();
                        core.setAlive(true);
                        logger.info(("登陆成功"));
                        break;
                    }
                    logger.info("4. 登陆超时，请重新扫描二维码图片");
                }

                logger.info("5. 登陆成功，微信初始化");
                if (!loginService.webWxInit()) {
                    logger.info("6. 微信初始化异常");
                    System.exit(0);
                }

                logger.info("开启微信状态检测线程");
                new Thread(new CheckLoginStatusThread()).start();

                logger.info("6. 开启微信状态通知");
                loginService.wxStatusNotify();

                logger.info("6. 清除。。。。");
                CommonTools.clearScreen();
                logger.info(String.format("欢迎回来， %s", core.getNickName()));

                logger.info("7. 开始接收消息");
                loginService.startReceiving();

                logger.info("8. 获取联系人信息");
                loginService.webWxGetContact();

                logger.info("9. 缓存本次登陆好友相关消息");
                WechatTools.setUserInfo(); // 登陆成功后缓存本次登陆好友相关消息（NickName, UserName）
                MsgCenter.handleMsg(wechatSimpleHandler);
            }
        }).start();
    }

    @Override
    public int status() {
        return core.isAlive()?0:-1;
    }

    @Override
    public void logout() {
        WechatTools.logout();
    }
}
