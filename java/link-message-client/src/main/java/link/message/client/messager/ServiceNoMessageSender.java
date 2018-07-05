package link.message.client.messager;

/**
 * 服务号消息发送者
 * @author zhongt
 *
 */
public class ServiceNoMessageSender extends MessageSender {
	public ServiceNoMessageSender(String fromId, String fromName, String fromCompany) {
		this.setFromType(MessageSendOrReceiverType.SERVICE_NO);
		this.setFromId(fromId);
		this.setFromName(fromName);
		this.setFromCompany(fromCompany);
	}
	
	@Override
	public void setFromType(String fromType) {
		super.setFromType(MessageSendOrReceiverType.SERVICE_NO);
	}
}