package top.wuchaofei.handler;

import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WechatSimpleHandler implements IMsgHandlerFace {
	@Value("${picPath}")
	private String picPath1;
	@Value("${voicePath}")
	private String voicePath1;
	@Value("${videoPath}")
	private String videoPath1;

	@Override
	public String textMsgHandle(JSONObject msg) {
		String text = msg.getString("Text");
		String result = "收到文本信息： " + text;
//		return result;
		return null;
	}

	@Override
	public String picMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".jpg"; // 这里使用收到图片的时间作为文件名
		String picPath = picPath1+ File.separator + fileName; // 保存图片的路径
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 调用此方法来保存图片
//		return "图片保存成功";
		return null;
	}

	@Override
	public String voiceMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".mp3"; // 这里使用收到语音的时间作为文件名
		String voicePath = voicePath1 + File.separator + fileName; // 保存语音的路径
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath); // 调用此方法来保存语音
//		return "声音保存成功";
		return null;
	}

	@Override
	public String viedoMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".mp4"; // 这里使用收到小视频的时间作为文件名
		String viedoPath = videoPath1 + File.separator + fileName;// 保存小视频的路径
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);// 调用此方法来保存小视频
//		return "视频保存成功";
		return null;
	}

	/*public static void main(String[] args) {
		IMsgHandlerFace msgHandler = new WechatSimpleHandler();
		String qrPath = "D:\\zhongliang\\itchat4jdemo\\src";
		// Wechat wechat = new Wechat(msgHandler,
		// "/home/itchat4j/demo/itchat4j/login");
		Wechat wechat = new Wechat(msgHandler, qrPath);
		wechat.start();
	}*/

	@Override
	public String nameCardMsgHandle(JSONObject arg0) {
//		return "收到名片消息";
		return null;
	}
}
