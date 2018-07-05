package link.message.client.content;

import link.message.client.StringOrObjectSerializable;

/**
 * 直接把字符串作为content
 * @author zhongt
 *
 */
public class StringContent implements StringOrObjectSerializable {
	private String text;
	
	public StringContent(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}