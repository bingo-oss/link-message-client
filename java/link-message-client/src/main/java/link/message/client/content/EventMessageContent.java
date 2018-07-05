/**
 * This file created at 2015年2月5日.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package link.message.client.content;

/**
 * 事件消息类型
 * 
 * @author zhongt
 */
public class EventMessageContent extends MessageContent {
	private String key;
	private String value;
	private String params;

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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}