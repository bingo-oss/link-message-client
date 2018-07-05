package link.message.client.content.complex;

import link.message.client.utils.Guard;

public class Param {
	protected String key;
	protected String value;
	
	protected Param() {
		
	}
	
	public Param(String key, String value) {
		Guard.guardReqiredString(key,   "key must be set value.");
		Guard.guardReqiredString(value, "key must be set value.");
		
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
