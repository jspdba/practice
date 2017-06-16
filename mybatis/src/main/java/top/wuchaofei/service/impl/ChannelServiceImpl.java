package top.wuchaofei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import top.wuchaofei.domain.Channel;
import top.wuchaofei.service.ChannelService;

import java.util.List;

/**
 * Created by cofco on 2017/6/15.
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements ChannelService{
    @Override
    public List<Channel> selectByName(Channel channel, int pageNo, int pageSize) {
        Example example = new Example(Channel.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(channel.getName())) {
            criteria.andLike("name", "%" + channel.getName() + "%");
        }

        if (channel.getId() != null) {
            criteria.andEqualTo("id", channel.getId());
        }
        //分页查询
        PageHelper.startPage(pageNo, pageSize);
        return selectByExample(example);
    }
}
