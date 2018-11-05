package link.message.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;

import link.message.client.content.MessageContent;
import link.message.client.content.StringContent;
import link.message.client.content.StringOrObjectSerializer;
import link.message.client.content.TextMessageContent;
import link.message.client.content.complex.ComplexMessageContent;
import link.message.client.content.complex.ComplexMessageContentSerializer;
import link.message.client.messager.MessageReceiver;
import link.message.client.messager.MessageSender;

/**
 * Emb消息封包
 * 
 * @author zhongt
 *
 */
public class Message {
	@JSONField(name="msg_id")
	String id;
	@JSONField(name="msg_type")
	int    type;
	@JSONField(name="to_device_types")
	private String toDeviceTypes;
	MessageContent  content;
	@JSONField(unwrapped=true)
	MessageSender   from;
	@JSONField(unwrapped=true)
	MessageReceiver to;
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getToDeviceTypes() {
		return toDeviceTypes;
	}

	public void setToDeviceTypes(String toDeviceTypes) {
		this.toDeviceTypes = toDeviceTypes;
	}

	public MessageContent getContent() {
		return content;
	}

	public void setContent(MessageContent content) {
		this.content = content;
	}

	public MessageSender getFrom() {
		return from;
	}

	public void setFrom(MessageSender from) {
		this.from = from;
	}

	public MessageReceiver getTo() {
		return to;
	}

	public void setTo(MessageReceiver to) {
		this.to = to;
	}
	
	public String toJson() {
		SerializeConfig config = new SerializeConfig();
		config.put(StringContent.class,         StringOrObjectSerializer.instance());
		config.put(TextMessageContent.class,    StringOrObjectSerializer.instance());
		config.put(ComplexMessageContent.class, ComplexMessageContentSerializer.instance());
		
		return JSON.toJSONString(this, config);
	}
	
	public static void main(String[] args) {
		 String message = "{\"msg_type\":\"8\",\"content\":{\"key\":\"click_menu\",\"value\":\"event\",\"params\":null},\"msg_id\":\"4446c2af041feaaf5ab008e35a37f800\",\"from_type\":1,\"from_id\":\"082418c1-1831-44ae-a0a9-83a093cb6d05\",\"from_name\":\"梁乐\",\"from_company\":\"gz-mstc\",\"to_type\":5,\"to_id\":\"3ecc8782-d1bd-45dc-88a5-b65d83dc5c30\",\"to_name\":\"服务上行\",\"to_company\":\"gz-mstc\",\"login_id\":\"liangle\"}";
		 JSONObject object = JSON.parseObject(message);
		 System.out.println(object);
		 object.get("content");
	}
}