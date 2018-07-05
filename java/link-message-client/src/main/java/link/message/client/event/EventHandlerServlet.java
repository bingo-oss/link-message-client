package link.message.client.event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import link.message.client.MessageClient;
import link.message.client.content.MessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.messager.PersonMessageReceiver;
import link.message.client.utils.SignatureUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户在服务号交付界面，通过手工输入或者菜单选择后，对应的应答处理器
 * @author zhongt
 */
public abstract class EventHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = -3952899882803583637L;
	private static final String ECHOSTR = "echostr";
	private static final String MESSAGE = "message";
	
	protected String token;
	protected String embUrl;
	protected String appId;
	protected String secret;

	@Override
	public void init() throws ServletException {
		token  = this.getInitParameter("token");
		embUrl = this.getInitParameter("embUrl");
		appId  = this.getInitParameter("appId");
		secret = this.getInitParameter("secret");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//检查请求是不是应该被处理
		if (!checkRequest(request)) {
			return;
		}
		
		//检查签名
		if (!checkSignature(request)) {
			return;
		}
		
		//如果是做可服务型校验
		if (request.getParameterMap().containsKey(ECHOSTR)) {
			doValidate(request, response);
		} else if (request.getParameterMap().containsKey(MESSAGE)) {
			doHandle(request, response);
		}
	}
	
	/**
	 * 做可服务性校验 
	 */
	protected void doValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.getWriter().write(request.getParameter(ECHOSTR));
			response.getWriter().flush();
		} finally {
			response.getWriter().close();
		}
	}
	
	/**
	 * 处理具体的事务请求
	 */
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*HttpRequestResult result = new HttpRequestResult(true, "");
		
		response.getWriter().write(JSON.toJSONString(result));
		response.getWriter().flush();
		response.getWriter().close();*/
		
		final JSONObject message = JSON.parseObject(request.getParameter(MESSAGE));
		
		JSONObject content = message.getJSONObject("content");
		final String key     = content.getString("key");
		final String value   = content.getString("value");
		final String params  = content.getString("params");
		final String loginId = message.getString("login_id");
		final String loginName = message.getString("from_name");
		
		new Runnable() {
			public void run() {
				MessageContent responseMessage = getReplyContent(loginId, loginName, key, value, params, message);
				if (null !=responseMessage) {
					MessageClient client = new MessageClient(embUrl, appId, secret);
					client.sendSingleMessage(responseMessage, new PersonMessageReceiver(loginId, loginName));
				}
			}
		}.run();
	}
	
	/**
	 * 根据用户的输入事件信息，计算相应的响应
	 * @param loginId 用户账号Id
	 * @param loginName 用户名称
	 * @param key 用户输入的类别
	 * @param value 用户输入的值
	 * @param params 与用户输入配套的可能参数
	 * @param userInputParams 其他完整的输入参数
	 * @return 消息内容体
	 */
	protected MessageContent getReplyContent(String loginId, String loginName, String key, String value, String params, JSONObject userInputParams) {
		return new TextMessageContent(loginName + " : " + UUID.randomUUID().toString());
	}
	
	/**
	 * 检查请求是否来自于Link
	 */
	@SuppressWarnings("rawtypes")
	protected boolean checkRequest(HttpServletRequest request) {
		Map params = request.getParameterMap();

		if(params.containsKey(SignatureUtil.TIMESTAMP)&&params.containsKey(SignatureUtil.NONCE)&&(params.containsKey(ECHOSTR)||params.containsKey(MESSAGE))) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检查签名
	 */
	protected boolean checkSignature(HttpServletRequest request) {
		String signature = request.getParameter(SignatureUtil.SIGNATURE);
		String timestamp = request.getParameter(SignatureUtil.TIMESTAMP);
		String nonce     = request.getParameter(SignatureUtil.NONCE);
         
		String[] otherParams = getOtherParams(request);
		
		// 检查签名
		String sign = SignatureUtil.getSignature(token, timestamp, nonce, otherParams);
		return sign.equals(signature);
	}
	
	/**
	 * 获取请求里的其他参数
	 * @param requestParams 请求参数
	 */
	protected String[] getOtherParams(HttpServletRequest request) {
		
		Map<String, String> otherParams = new HashMap<String, String>();

		for (Object key : request.getParameterMap().keySet()) {
			String keyStr = key.toString();
			if (keyStr.equalsIgnoreCase(SignatureUtil.TIMESTAMP) || keyStr.equalsIgnoreCase(SignatureUtil.NONCE) || keyStr.equalsIgnoreCase(SignatureUtil.SIGNATURE)) {
				continue;
			}
			otherParams.put(keyStr, request.getParameter(keyStr));
		}
		
		return otherParams.values().toArray(new String[] {});
	}
}
