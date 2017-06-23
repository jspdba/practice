#实践项目
##已包含模块包括
    springmvc,
    shiro,
    mybatis, 
    Mybatis-PageHelper
    Mapper,
    
    freemaker,
    admieLte,
    mysql,
    redis
##使用说明
1. 创建数据库wuchaofei
2. 运行wuchaofei.sql
3. 配置generator/config.properties，及各个配置文件，修改常量
##若添加新表运行
    mybatis-generator 命令(/practice/mybatis目录下执行)
    mybatis-generator:generate
## 加密方式改变
    之前用md5加密，现在用md5+salt(用户名)方式加密
    具体方法参考PasswordHelper.java
    之前密码
        jspdba/A1F60A3530A8BFE732C0BD72BC509A7F
    之后密码
        密文
            jspdba/839d39be7c7f99c9cd6cf2af4f23056f
        明文
            jspdba/(wuchaofei,jspdba)
##生成证书
    keytool -genkey -alias casserver -keypass wuchaofei -keyalg RSA  -keystore casserver.keystore  -validity 365
    说明：-alias指定别名为casserver；-keyalg指定RSA算法；-keypass指定私钥密码；-keystore指定密钥文件名称为casserver.keystore；-validity指定有效期为365天。另外提示的输入keystore密码应与-keypass指定的相同；您的名字与姓氏是CAS服务器使用的域名（不能是IP，也不能是localhost），其它项随意填。
    keytool -export -alias casserver -storepass wuchaofei -file casserver.cer -keystore casserver.keystore
    keytool -import -trustcacerts -alias casserver -storepass wuchaofei  -file casserver.cer -keystore cacerts
    
    客户端导入证书
    keytool -import -trustcacerts -alias casserver -storepass wuchaofei  -file casserver.cer -keystore cacerts 
    
    生成客户端密钥库文件 
    单向认证的客户端配置只需生成客户端信任文件caserts即可。首先将服务端生成的证书文件（之前生成的casserver.cer文件）复制到$JAVA_HOME/jre/lib/security下，然后打开CMD窗口切换到$JAVA_HOME/jre/lib/security下并执行命令： 
    keytool -import -trustcacerts -alias casclient -storepass changeit -file casserver.cer -keystore cacerts    
    tomcat 配置
    <Connector SSLEnabled="true" clientAuth="false"     
        keystoreFile="D:/Java/apache/apache-tomcat-cas/casserver.keystore"     
        keystorePass="demosso"     
        maxThreads="150"     
        port="443"     
        protocol="org.apache.coyote.http11.Http11Protocol"     
        scheme="https" secure="true" sslProtocol="TLS"/>    
    <!--keystoreFile 生成的安全证书的位置 -->    
    <!--keystorePass设置安全证书的密码--> 
    
    
#参考
1. [Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
2. [MyBatis通用Mapper3](https://github.com/abel533/Mapper)
3. [使用Mapper专用的MyBatis Generator插件](http://git.oschina.net/free/Mapper/blob/master/wiki/mapper3/7.UseMBG.md)
4. [github](https://github.com/yaphone/itchat4j/tree/96bf71c5be3624dfdcb51dd6f2fdec466ce9f552)
5. [跟开涛学shiro](http://jinnianshilongnian.iteye.com/blog/2018398)
6. [shiro验证码部分参考](http://www.mamicode.com/info-detail-469801.html)
7. [shiro验证码部分参考](http://blog.csdn.net/zilong_zilong/article/details/65450138)
8. [Shiro笔记(一)----Shiro安全框架简介](http://blog.csdn.net/u011781521/article/details/55094751)