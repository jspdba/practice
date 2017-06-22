package top.wuchaofei.filter;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cofco on 2017/6/20.
 */
public class KaptchaFilter extends AccessControlFilter {
    private String kaptchaParam = "kaptcha";// 前台提交的验证码参数名
    private boolean captchaEbabled=true;//是否使用验证码

    public boolean isCaptchaEbabled() {
        return captchaEbabled;
    }

    public void setCaptchaEbabled(boolean captchaEbabled) {
        this.captchaEbabled = captchaEbabled;
    }

    public String getKaptchaParam() {
        return kaptchaParam;
    }

    public void setKaptchaParam(String kaptchaParam) {
        this.kaptchaParam = kaptchaParam;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("captchaEbabled", captchaEbabled);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);

        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (captchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }

        // 验证验证码是否正确
        if (null == request.getParameter(kaptchaParam)) {
            return true;
        } else {
            String kaptchaFromWeb = (String) request.getParameter(kaptchaParam);
            String kaptchaFromSession = (String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

            if(kaptchaFromSession==null){
                return false;
            }
            return kaptchaFromSession.toUpperCase().equals(kaptchaFromWeb.toUpperCase());
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 如果验证码失败了，存储失败key属性
        request.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "Kaptcha.error");
        return true;
    }
}
