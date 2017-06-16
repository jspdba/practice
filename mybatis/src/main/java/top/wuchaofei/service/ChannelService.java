package top.wuchaofei.service;

import org.springframework.stereotype.Service;
import top.wuchaofei.domain.Channel;

import java.util.List;

/**
 * Created by cofco on 2017/6/15.
 */
@Service
public interface ChannelService extends BaseService<Channel> {
    public List<Channel> selectByName(Channel channel, int page, int rows);
}