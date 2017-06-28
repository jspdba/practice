package sso.top.wuchaofei.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sso.top.wuchaofei.domain.User;
import sso.top.wuchaofei.mapper.UserMapper;
import sso.top.wuchaofei.service.ServiceTicketCacheService;
import sso.top.wuchaofei.service.TicketGrantCookieCacheService;
import sso.top.wuchaofei.service.UserService;
import sso.top.wuchaofei.utils.CookieUtil;
import sso.top.wuchaofei.utils.UUIDGeneratorUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cofco on 2017/6/26.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    @Qualifier("serviceTicketCacheServiceImpl")
    ServiceTicketCacheService serviceTicketCacheService;
    @Autowired
    TicketGrantCookieCacheService ticketGrantCookieCacheService;

    @Value("${cookie.tgc.name}")
    String TGC_COOKIE_NAME;
    @Value("${cookie.tgc.domain}")
    String TGC_COOKIE_DOMAIN;
    @Value("${cookie.tgc.expire}")
    int TGC_COOKIE_EXPIRE;


    protected final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd) {

        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", passwd);

        List<User> userList = userMapper.selectByExample(example);
        User user = null;
        if(userList.size()>0){
            user = userList.get(0);
            request.getSession().setAttribute("user",user);
        }
        return user;
    }

    @Override
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        // TODO: 2017/6/28 删除cookie ，删除 TGC serviceTicket
        request.getSession().removeAttribute("user");
        String serviceTicket = request.getParameter("serviceTicket");
        if(StringUtils.isNotBlank(serviceTicket)){
            deleteServiceTicket(serviceTicket);
        }
        String TGC = CookieUtil.getCookieValue(request, TGC_COOKIE_NAME);
        if(StringUtils.isNotBlank(TGC)){
            ticketGrantCookieCacheService.del(TGC);
        }
        CookieUtil.deleteCookie(response,TGC_COOKIE_NAME, TGC_COOKIE_DOMAIN);
        return true;
    }

    @Override
    public User selectByEntry(User record) {
        return userMapper.selectOne(record);
    }

    @Override
    public User findUserByName(String loginName) {
        User u=userMapper.findUserByName(loginName);
        logger.info(u.toString());
        return u;
    }


    @Override
    public List<User> listByDataTable(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("iDisplayStart"), (Integer) map.get("iDisplayLength"));
        return userMapper.listByPager(buildPagerMap(map));
    }

    @Override
    public String genAndStoreServiceTicket(String tgc) {
        String serviceTicket = genServiceTicket();
        storeServiceTicket(serviceTicket,tgc);
        return serviceTicket;
    }

    //存储
    private void storeServiceTicket(String serviceTicket, String tgc) {
        serviceTicketCacheService.setEx(serviceTicket, tgc);
    }

    private String genServiceTicket() {
        return UUIDGeneratorUtils.getUUID();
    }
    private String genTicketGrantedCookie() {
        return UUIDGeneratorUtils.getUUID();
    }

    /**
     * 转换为可接受的分页map
     * @param map
     * @return
     */
    private static Map buildPagerMap(Map map){
        Map<String,Object> map1=new HashMap<String,Object>();
        //注意字段用sql关键字的情况
        String orderByClause;
        Object iSortCol_0 = map.get("iSortCol_0");
        Object sSortDir_0 = map.get("sSortDir_0");

        String order_colume= iSortCol_0==null?null:toCamels((String)map.get("mDataProp_"+iSortCol_0));
        orderByClause = (order_colume+" "+(sSortDir_0==null?"asc":(String)sSortDir_0));
        map1.put("orderByClause",orderByClause);
        return map1;
    }

    private static String toCamels(String str){
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(str),"_");
    }

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

    //为客户端设置一个tgc,并缓存起来
    @Override
    public String grantTicketGrantedCookie(HttpServletResponse response, User user) {
        //生产tgc
        String tgc = genTicketGrantedCookie();
        //缓存tgc
        ticketGrantCookieCacheService.setEx(tgc,user.getUsername());
        //客户端cookie tgc
        CookieUtil.addCookie(response,TGC_COOKIE_NAME,tgc,TGC_COOKIE_DOMAIN,TGC_COOKIE_EXPIRE);
        return tgc;
    }

    @Override
    public boolean isExpireOfServiceTicket(String serviceTicket) {
        return serviceTicketCacheService.isExpired(serviceTicket);
    }

    @Override
    public boolean isExpireOfTicketGrantCookie(String tgc) {
        return ticketGrantCookieCacheService.isExpired(tgc);
    }

    @Override
    public String getUserInfoByServiceTicket(String serviceTicket) {
        //用户信息保存在tgc里，而非serviceTicket里
        String tgc = (String) serviceTicketCacheService.get(serviceTicket);
        return (String)ticketGrantCookieCacheService.get(tgc);
    }

    @Override
    public String getTicketGrantCookieFromRequest(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request,TGC_COOKIE_NAME);
    }

    @Override
    public Object getUserInfoByTicketGrantCookie(String tgc) {
        return ticketGrantCookieCacheService.get(tgc);
    }

    @Override
    public void deleteServiceTicket(String serviceTicket) {
        serviceTicketCacheService.del(serviceTicket);
    }
}
