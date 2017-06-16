package top.wuchaofei.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.wuchaofei.domain.Channel;
import top.wuchaofei.service.ChannelService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @RequestMapping("list")
    public String list(ModelMap modelMap,Channel channel,
                       @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "10") int pageSize ){
//        List<Channel> list = channelService.selectPage(pageNo,pageSize);
        List<Channel> list = channelService.selectByName(channel,pageNo,pageSize);
        if(list==null){
            list=new ArrayList<Channel>();
        }
        modelMap.addAttribute("list",list);
        modelMap.addAttribute("pageInfo", new PageInfo<Channel>(list));
        modelMap.addAttribute("title","列表");
        return "channel/list";
    }
    @RequestMapping("edit")
    public String edit(Channel channel, ModelMap modelMap){
        if(channel.getId()!=null){
            Channel channel1=channelService.selectByPrimaryKey(channel.getId());
            modelMap.addAttribute("channel",channel1);
            modelMap.addAttribute("title","编辑");
            modelMap.addAttribute("edit",true);
        }else{
            modelMap.addAttribute("edit",false);
            modelMap.addAttribute("title","添加");
        }
        return "channel/edit";
    }
    @RequestMapping("save")
    public String save(Channel channel){
        if(channel.getId()==null){
            channelService.save(channel);
        }else{
            channelService.updateByPrimaryKeySelective(channel);
        }
        return "redirect:/channel/list.action";
    }
    @RequestMapping("delete")
    public String delete(Channel channel){
        if(channel.getId()!=null){
            channelService.delete(channel);
        }
        return "redirect:/channel/list.action";
    }
}
