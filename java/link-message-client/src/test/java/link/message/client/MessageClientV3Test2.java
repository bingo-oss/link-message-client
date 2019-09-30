package link.message.client;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import link.message.client.IMConfig;
import link.message.client.MessageClientV3;
import link.message.client.SendMessageResult;
import link.message.client.auth.AccessTokenProvider;
import link.message.client.auth.AccessTokenProviderImpl;
import link.message.client.content.MessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.messager.MessageSender;
import link.message.client.messager.PersonMessageReceiver;

public class MessageClientV3Test2 {

	protected MessageClientV3 messageClient;
	
	@Before
	public void setUp() throws Exception {
		String imUrl = "http://msg.bingolink.biz:10082";
		IMConfig config = new IMConfig(imUrl);
		
		String tokenUrl = "https://www.bingolink.biz/sso/oauth2/token";
		String clientId = "ja6Nx65J6h4KzjwD7TYp";
		String clientSecret = "cDKt87HYSxZ8tHMj6KmcT28MpZGe7J";
		AccessTokenProvider tp = new AccessTokenProviderImpl(tokenUrl, clientId, clientSecret);
		
		messageClient = new MessageClientV3(config, tp);
	}
	
	@Test
	public void testSendTextMessage() {
		// 构造简单文本消息
		MessageContent msg = new TextMessageContent("简单文本消息");
		// 设置接收者设备类型
		msg.setToDeviceTypes("02");
		MessageSender from = new MessageSender();
		// 设置服务号ID
		from.setFromId("9094f79d-4c04-4185-bb71-dfd4a818ee91");
		msg.setFrom(from);
		// 发送消息给指定用户
		SendMessageResult result = messageClient.sendSingleMessage(msg, new PersonMessageReceiver("pengweize1", "彭伟泽"));
//		System.out.println(result.getError());
		Assert.assertEquals(true, result.isSuccess());
	}
}