package top.wuchaofei.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Created by zheng.zhang on 2016/4/21.
 */
public class MD5Util {
    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
    // 用来将字节转换成 16 进制表示的字符
    static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public final static String md5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5File(String path){
        File f =new File(path);
        return md5File(f);
    }

    public static String md5File(File f){
        if(f.exists()){
            FileInputStream fis = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                logger.info("MD5摘要长度：" + md.getDigestLength());
                fis = new FileInputStream(f);
                byte[] buffer = new byte[2048];
                int length = -1;
                logger.info("开始生成摘要");
                long s = System.currentTimeMillis();
                while ((length = fis.read(buffer)) != -1) {
                    md.update(buffer, 0, length);
                }
                logger.info("摘要生成成功,总用时: "
                        + (System.currentTimeMillis() - s) + "ms");
                byte[] b = md.digest();
                return byteToHexString(b);
                // 16位加密
                // return buf.toString().substring(8, 24);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                ex.printStackTrace();
                return null;
            }finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }
    /**
     * 把byte[]数组转换成十六进制字符串表示形式
     * @param tmp    要转换的byte[]
     * @return 十六进制字符串表示形式
     */
    private static String byteToHexString(byte[] tmp) {
        String s;
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        s = new String(str); // 换后的结果转换为字符串
        return s;
    }
    public static void main(String[] args) {
        System.out.println(md5("wuchaofei"));
    }
}
