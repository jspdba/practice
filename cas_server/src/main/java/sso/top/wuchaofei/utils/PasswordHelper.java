package sso.top.wuchaofei.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import sso.top.wuchaofei.domain.User;

/**
 * Created by cofco on 2017/6/21.
 */
public class PasswordHelper {
    /*private RandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();*/
    private static final String algorithmName = "md5";
    private static final int hashIterations = 1;
    public static void encryptPassword(User user) {
//        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }

    /**
     * 生成密码
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password,String salt) {
        return new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();
    }

    public String md5(String str,String salt){
        return new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
    }
    public String md5(String str,String salt,int times){
        return new Md5Hash(str, salt,times).toString();//还可以转换为 toBase64()/toHex()
    }

    public String sha256(String str,String salt){
        return new Sha256Hash(str, salt).toString();
    }

    public String hash(String str,String salt){
        return hash("SHA-1",str,salt);
    }

    /**
     * shiro专用
     * sha加密算法
     * @param algorithmName
     * @param str
     * @param salt
     * @return
     */
    public String hash(String algorithmName, String str,String salt){
        //内部使用MessageDigest
        String simpleHash = new SimpleHash(algorithmName, str, salt).toString();
        return simpleHash;
    }

    /**
     * 仅仅是个例子
     * @return
     */
    public String shiroHash(){
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        return hex;
    }


    /*public static void main(String[] args) {
        User user=new User();
        user.setUsername("jspdba");
        user.setPassword("wuchaofei");
        encryptPassword(user);
        System.out.println(user.getPassword());
    }*/

    public static void main(String[] args) {
        System.out.println(encryptPassword("wuchaofei","test1"));
        System.out.println(encryptPassword("wuchaofei","test2"));
        System.out.println(encryptPassword("wuchaofei","test3"));
        System.out.println(encryptPassword("wuchaofei","test4"));
        System.out.println(encryptPassword("wuchaofei","test5"));
        System.out.println(encryptPassword("wuchaofei","test6"));
        System.out.println(encryptPassword("wuchaofei","test7"));
        System.out.println(encryptPassword("wuchaofei","test8"));
        System.out.println(encryptPassword("wuchaofei","test9"));
        System.out.println(encryptPassword("wuchaofei","test10"));
        System.out.println(encryptPassword("wuchaofei","test11"));
    }
}

