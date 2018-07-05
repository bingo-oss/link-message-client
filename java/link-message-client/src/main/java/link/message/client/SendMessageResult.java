package link.message.client;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息发送的结果
 * 
 * @author zhongt
 *
 */
public class SendMessageResult {
	private boolean success;
	@JSONField(name="err")
	private String error;

	public SendMessageResult() {
		
	}
	
	public SendMessageResult(boolean success, String error) {
		this.success = success;
		this.error   = error;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}