package link.message.client.content.complex.linear;

/**
 * 表单字段对应的值信息
 * 
 * @author zhongt
 *
 */
public class FormFieldValue {
	protected String key;
	protected String value;
	
	public FormFieldValue(String key, String value) {
		this.key   = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}