package link.message.client.messager;

import java.util.List;

import link.message.client.utils.Guard;

/**
 * 批量的消息接受者
 * @author zhongt
 *
 */
public class MultiMessageReceiver extends MessageReceiver {
	protected int idType;
	protected int length = 0;
	
	/**
	 * 批量消息接收人员构造
	 * @param personMessageReceivers       消息接受者集合
	 * @param personMessageReceiverIdType  消息接受者的ID类型  1为loginId 2为userId
	 */
	public MultiMessageReceiver(List<PersonMessageReceiver> personMessageReceivers, int personMessageReceiverIdType) {
		Guard.guardReqiredCollection(personMessageReceivers, "message receivers object must be set value.");
		Guard.guardReqiredEnumValue(personMessageReceiverIdType, "1,2", "message receiver type must be set to 1 or 2");
		
		this.idType = personMessageReceiverIdType;
		
		StringBuilder toIds       = new StringBuilder();
		StringBuilder toNames     = new StringBuilder();
		StringBuilder toTypes     = new StringBuilder();
		StringBuilder toCompanies = new StringBuilder();
		
		for (MessageReceiver receiver: personMessageReceivers) {
			toIds.append(",").append(receiver.getToId());
			toNames.append(",").append(receiver.getToName());
			toTypes.append(",").append(receiver.getToType());
			// 公司是可选的
			if (null != receiver.getToCompany() && receiver.getToCompany().trim().length() > 0) {
				toCompanies.append(",").append(receiver.getToCompany());
			}
			
			// 设置接受者的个数
			length++;
		}
		
		this.toId   = toIds.substring(1);
		this.toName = toNames.substring(1);
		this.toType = toTypes.substring(1);
		
		if (toCompanies.length() > 0) {
			this.toCompany = toCompanies.substring(1);
		}
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}