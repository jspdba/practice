package top.wuchaofei.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuchaofei.domain.User;
import top.wuchaofei.manager.UserManager;
import top.wuchaofei.mapper.UserMapper;
import top.wuchaofei.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wuchaofei  on 2017/3/30.
 */
@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    UserMapper userMapper;

    protected final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(User record) {
        return userMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }


    @Override
    public User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd) {
        User user=new User();

        user.setUsername(username);
        user.setPassword(MD5Util.md5(passwd));

        user = userMapper.selectByEntry(user);

        logout(request, response);
        if(user!=null){
            request.getSession().setAttribute("user",user);
        }

        return user;
    }

    @Override
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("user")!=null){
            request.getSession().removeAttribute("user");
        }
        return true;
    }

    @Override
    public User selectByEntry(User record) {
        return userMapper.selectByEntry(record);
    }

    @Override
    public User findUserByName(String loginName) {
        User u=userMapper.findUserByName(loginName);
        logger.info(u.toString());
        return u;
    }


    @Override
    public List<User> listByDataTable(Map<String, Object> map) {
        return userMapper.listByPager(buildPagerMap(map));
    }

    @Override
    public long getTotalByDataTable(Map<String, Object> map) {
        return userMapper.getTotalByPager(buildPagerMap(map));
    }

    /**
     * 转换为可接受的分页map
     * @param map
     * @return
     */
    private static Map buildPagerMap(Map map){
        Map<String,Object> map1=new HashMap<String,Object>();
        if(map==null){
            map.put("start",0);
            map.put("limit",10);
            return map1;
        }
        map1.put("start",map.get("iDisplayStart"));
        map1.put("limit",map.get("iDisplayLength"));
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
}
