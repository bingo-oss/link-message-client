package link.message.client.test;

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

public class MessageClientV3Test {

	protected MessageClientV3 messageClient;
	
	@Before
	public void setUp() throws Exception {
		String imUrl = "http://10.201.76.141:10082";
		IMConfig config = new IMConfig(imUrl);
		
		String tokenUrl = "http://10.201.76.141:8088/sso/oauth2/token";
		String clientId = "clientId";
		String clientSecret = "clientSecret";
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
		from.setFromId("001f310c-b03a-450a-b54c-a5fd3db6580f");
		msg.setFrom(from);
		// 发送消息给指定用户
		SendMessageResult result = messageClient.sendSingleMessage(msg, new PersonMessageReceiver("admin", "管理员"));
//		System.out.println(result.getError());
		Assert.assertEquals(true, result.isSuccess());
	}
}