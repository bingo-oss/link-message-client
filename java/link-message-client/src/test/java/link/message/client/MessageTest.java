package link.message.client;

import org.junit.Test;

import link.message.client.content.MessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.messager.MessageSender;

public class MessageTest {

	@Test
	public void testToJson() {
		// 构造简单文本消息
		MessageContent messageContent = new TextMessageContent("简单文本消息");
		// 设置接收者设备类型
		messageContent.setToDeviceTypes("02");
		MessageSender from = new MessageSender();
		// 设置服务号ID
		from.setFromId("001f310c-b03a-450a-b54c-a5fd3db6580f");
		messageContent.setFrom(from);
		Message message = new Message();
		message.setType(messageContent.getType());
		message.setFrom(messageContent.getFrom());
		message.setToDeviceTypes(messageContent.getToDeviceTypes());
		message.setContent(messageContent);
		
		String testResult = message.toJson();
		System.out.println(testResult);
	}
}