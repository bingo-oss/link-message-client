package link.message.client.content;

import com.alibaba.fastjson.annotation.JSONField;

import link.message.client.messager.MessageSender;

/**
 * 消息详细内容
 * 
 * @author zhongt
 *
 */
public abstract class MessageContent {
	// 消息类型
	@JSONField(serialize=false, deserialize=false)
	protected int type;
	/**发送者*/
	private MessageSender from;
	/**
	 * 01:web端; 02:手机端 ; 03:pc端
	 */
	@JSONField(name="to_device_types")
	private String toDeviceTypes;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public MessageSender getFrom() {
		return from;
	}

	public void setFrom(MessageSender from) {
		this.from = from;
	}

	public String getToDeviceTypes() {
		return toDeviceTypes;
	}

	public void setToDeviceTypes(String toDeviceTypes) {
		this.toDeviceTypes = toDeviceTypes;
	}
}