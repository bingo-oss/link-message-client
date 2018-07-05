package link.message.client.messager;

/**
 * 个人消息接受者
 * @author zhongt
 *
 */
public class PersonMessageReceiver extends MessageReceiver {
	
	public PersonMessageReceiver() {
		
	}
	
	public PersonMessageReceiver(String id, String name) {
		this.toId   = id;
	    this.toName = name;
	}

	@Override
	public String getToType() {
		return MessageSendOrReceiverType.PERSON;
	}

	@Override
	public void setToType(String toType) {
		super.setToType(MessageSendOrReceiverType.PERSON);
	}
}