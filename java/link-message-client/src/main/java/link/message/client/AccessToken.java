package link.message.client;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 服务号发消息预先取得的访问令牌
 * 
 * @author zhongt
 *
 */
public class AccessToken {
	private boolean success;
	@JSONField(name="access_token")
	private String accessToken;
	private Date expire;
	private String error;

	public AccessToken() {
		
	}
	
	public AccessToken(boolean success, String error) {
		this.success = success;
		this.error   = error;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}