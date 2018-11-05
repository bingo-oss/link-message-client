package link.message.client.messager;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息发送者
 * @author Etomy
 */
public class MessageSender {
	
	@JSONField(name="from_id")
	private String fromId;
	@JSONField(name="from_name")
	private String fromName;
	@JSONField(name="from_type")
	private String fromType;
	@JSONField(name="from_company")
	private String fromCompany;

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}
}