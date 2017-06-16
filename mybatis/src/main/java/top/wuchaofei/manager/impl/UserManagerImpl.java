package top.wuchaofei.manager.impl;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.wuchaofei.domain.Channel;
import top.wuchaofei.domain.User;
import top.wuchaofei.manager.UserManager;
import top.wuchaofei.mapper.UserMapper;
import top.wuchaofei.service.BaseService;
import top.wuchaofei.service.impl.BaseServiceImpl;
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
public class UserManagerImpl extends BaseServiceImpl<User> implements UserManager {
    @Autowired
    UserMapper userMapper;

    protected final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd) {

        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (username != null) {
            criteria.andEqualTo("username", username);
        }
        if (passwd != null) {
            criteria.andEqualTo("password", MD5Util.md5(passwd));
        }
        User user = (User) userMapper.selectByExample(example);

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
}
