package sso.top.wuchaofei.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cofco on 2017/6/15.
 */
@Service
public interface BaseService<T> {

    public int save(T entity);

    public int delete(T entity);

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum, int pageSize);

    public T selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(T entity);

    List<T> selectByExample(Object example);
}
