package link.message.client.messager;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息接收者
 * @author Etomy
 */
public class MessageReceiver {

	@JSONField(name="to_id")
	protected String toId;
	@JSONField(name="to_name")
	protected String toName;
	@JSONField(name="to_type")
	protected String toType;
	@JSONField(name="to_company")
	protected String toCompany;

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToType() {
		return toType;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}

	public String getToCompany() {
		return toCompany;
	}

	public void setToCompany(String toCompany) {
		this.toCompany = toCompany;
	}
}
