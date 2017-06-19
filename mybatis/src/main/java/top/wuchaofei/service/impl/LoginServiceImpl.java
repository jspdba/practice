package top.wuchaofei.service.impl;

import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.utils.enums.URLEnum;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by cofco on 2017/6/18.
 */
@Service
public class LoginServiceImpl extends cn.zhouyafeng.itchat4j.service.impl.LoginServiceImpl {
    private static Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    private Core core = Core.getInstance();

    @Override
    public boolean getQR(String qrPath) {
        qrPath = qrPath + File.separator + "QR.jpg";
        String qrUrl = URLEnum.QRCODE_URL.getUrl() + core.getUuid();
        HttpEntity entity = core.getMyHttpClient().doGet(qrUrl, null, true, null);
        try {
            OutputStream out = new FileOutputStream(qrPath);
            byte[] bytes = EntityUtils.toByteArray(entity);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return false;
        }

        return true;
    }
}
