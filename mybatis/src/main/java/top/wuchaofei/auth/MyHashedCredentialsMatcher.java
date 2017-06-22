package top.wuchaofei.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;


/**
 * Created by cofco on 2017/6/21.
 */
public class MyHashedCredentialsMatcher extends org.apache.shiro.authc.credential.HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}
