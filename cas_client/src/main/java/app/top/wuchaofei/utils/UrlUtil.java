package app.top.wuchaofei.utils;

import org.springframework.util.Assert;

/**
 * Created by cofco on 2017/6/28.
 */
public class UrlUtil {
    public String addParameter(String redirectUrl, String key, String value) {
        Assert.notNull(redirectUrl);
        int startIndex = redirectUrl.indexOf(key);
        if(startIndex<0){
            return redirectUrl+"?"+key+"="+value;
        }
        int lastIndex = redirectUrl.indexOf("&",startIndex);

        StringBuilder sb=new StringBuilder();
        sb.append(redirectUrl.substring(0,startIndex));
        sb.append(key).append("=");
        sb.append(value);
        if(lastIndex>0){
            sb.append(redirectUrl.substring(lastIndex));
        }
        return sb.toString();
    }
    public static String removeParameter(String redirectUrl, String key) {
        Assert.notNull(redirectUrl);
        int startIndex = redirectUrl.indexOf(key);
        if(startIndex<0){
            return redirectUrl;
        }
        int lastIndex = redirectUrl.indexOf("&",startIndex);

        StringBuilder sb=new StringBuilder();
        sb.append(redirectUrl.substring(0,startIndex-1));
        if(lastIndex>0){
            if(sb.lastIndexOf("?")>0){
                sb.append("&");
            }else{
                sb.append("?");
            }
            sb.append(redirectUrl.substring(lastIndex+1));
        }
        return sb.toString();
    }
}
