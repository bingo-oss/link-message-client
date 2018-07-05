package link.message.client;


/**
 * Http请求返回的结果
 * 
 * @author zhongt
 *
 */
public class HttpRequestResult {
	private boolean success = false;
	private String  result;

	public HttpRequestResult() {

	}

	public HttpRequestResult(boolean success, String result) {
		this.success = success;
		this.result  = result;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}