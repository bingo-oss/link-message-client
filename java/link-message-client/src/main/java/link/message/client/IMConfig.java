package link.message.client;

/**
 * 消息服务客户端配置对象
 * 
 * @author kael.
 */
public class IMConfig {

	/**消息服务器地址*/
	protected String imUrl;
	/**发送用户消息请求地址*/
	protected String userUrl;
	/**接收用户消息请求地址*/
	protected String pollUrl;
	/**发送服务号消息请求地址*/
	protected String snoUrl;
	/**发消息给服务号粉丝请求地址*/
	protected String sno2fansUrl;

	public IMConfig(String imUrl) {
		this.imUrl = imUrl;
		this.autoConfigure(imUrl);
	}

	public String getImUrl() {
		return imUrl;
	}

	public void setImUrl(String imUrl) {
		this.imUrl = imUrl;
	}

	public void autoConfigure(String embUrl) {
		if (null == embUrl || embUrl.trim().isEmpty()) {
			return;
		}
		String url = embUrl.endsWith("/") ? embUrl.substring(0, embUrl.length() - 1) : embUrl;

		this.userUrl = url + "/private/send";
		this.pollUrl = url + "/private/poll";
		this.snoUrl = url + "/svrnum/send";
		this.sno2fansUrl = url + "/svrnum/send2fans";
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getSnoUrl() {
		return snoUrl;
	}

	public void setSnoUrl(String snoUrl) {
		this.snoUrl = snoUrl;
	}

	public String getSno2fansUrl() {
		return sno2fansUrl;
	}

	public void setSno2fansUrl(String sno2fansUrl) {
		this.sno2fansUrl = sno2fansUrl;
	}

	public String getPollUrl() {
		return pollUrl;
	}

	public void setPollUrl(String pollUrl) {
		this.pollUrl = pollUrl;
	}
}