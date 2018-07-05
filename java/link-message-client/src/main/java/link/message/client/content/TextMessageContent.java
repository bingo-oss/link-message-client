package link.message.client.content;

import link.message.client.MessageType;
import link.message.client.StringOrObjectSerializable;
import link.message.client.utils.Guard;

/**
 * 简单文本消息内容
 * @author zhongt
 *
 */
public class TextMessageContent extends MessageContent implements StringOrObjectSerializable {
	private String text;
	
	public TextMessageContent(String text) {
		Guard.guardReqiredString(text, "TextMessageContent's content is required.");
		
		this.text = text;
		this.type = MessageType.TEXT.value();
	}

	@Override
	public String toString() {
		return text;
	}
}
