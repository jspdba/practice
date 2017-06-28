package sso.top.wuchaofei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sso.top.wuchaofei.service.BaseService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by cofco on 2017/6/15.
 */
@Service
public abstract  class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    protected Mapper<T> mapper;

    public int save(T entity){
        return mapper.insertSelective(entity);
    }

    public T selectByPrimaryKey(long id){
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T channel) {
        return mapper.updateByPrimaryKeySelective(channel);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int delete(T entity){
        return mapper.deleteByPrimaryKey(entity);
    }

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //Spring4支持泛型注入
        return mapper.select(null);
    }
}
