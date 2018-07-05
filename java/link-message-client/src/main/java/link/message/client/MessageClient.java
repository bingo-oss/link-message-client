package link.message.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import link.message.client.content.MessageContent;
import link.message.client.messager.MessageReceiver;
import link.message.client.messager.MultiMessageReceiver;
import link.message.client.messager.PersonMessageReceiver;
import link.message.client.messager.PersonMessageReceiverIdType;
import link.message.client.utils.Guard;

/**
 * 消息服务Http客户端
 * 
 * @author zhongt
 *
 */
public class MessageClient {
	private static final String TOKEN = "token";
	private static final String SEND = "send";
	
	protected static final String INVALID_ACCESS_TOKEN = "invalid access_token";

	private String embServiceUrl; // emb服务的地址，比如http://co3.gz-mstc.com:10082/svrnum/

	private String appId;
	private String secretKey;

	private String tokenGetServiceUrl; // 获取token的服务地址
	private String messageSendServiceUrl; // 发送消息的服务地址

	protected HttpClient  httpClient;
	protected AccessToken accessToken;

	/**
	 * @param embServiceUrl emb服务的地址
	 * @param appId         服务号Id
	 * @param secretKey     服务号密钥
	 * @param httpClient    请求远程http服务的客户端对象，为空则采用默认的httpClient
	 */
	public MessageClient(String embServiceUrl, String appId, String secretKey, HttpClient httpClient) {
		Guard.guardReqiredString(embServiceUrl, "emb's service url is required.");
		Guard.guardReqiredString(appId, "service no's id (appId) is required.");
		Guard.guardReqiredString(secretKey, "service no's secret key is required.");

		this.embServiceUrl = embServiceUrl.trim().endsWith("/") ? embServiceUrl.trim() : embServiceUrl + "/";
		this.appId = appId.trim();
		this.secretKey = secretKey.trim();
		this.httpClient = httpClient;
		
		this.tokenGetServiceUrl = this.embServiceUrl + TOKEN;
		this.messageSendServiceUrl = this.embServiceUrl + SEND;

		if (null == httpClient) {
			this.httpClient = getDefaultHttpClient();
		}
	}
	
	private HttpClient getDefaultHttpClient() {
		SSLContextBuilder          builder = new SSLContextBuilder();
		SSLConnectionSocketFactory sslsf   = null;
	    try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslsf = new SSLConnectionSocketFactory(builder.build());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	    
	    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	/**
	 * @param embServiceUrl emb服务的地址
	 * @param appId         服务号Id
	 * @param secretKey     服务号密钥
	 */
	public MessageClient(String embServiceUrl, String appId, String secretKey) {
		this(embServiceUrl, appId, secretKey, null);
	}

	public AccessToken getAccessToken() {
		// 检查token过期
		if (null != accessToken && accessToken.isSuccess() && accessToken.getExpire().after(new Date())) {
			return accessToken;
		}
		
		return getNewAccessToken();
	}
	
	protected AccessToken getNewAccessToken() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "client_credential"));
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("secret", secretKey));

		HttpRequestResult result = doPostQuietly(this.tokenGetServiceUrl, params);
		
		// 如果调用rest服务出问题了，那么就直接accessToken
		if (!result.isSuccess()) {
			accessToken = new AccessToken(result.isSuccess(), result.getResult());
			return accessToken;
		}
		
		// 缓存accessToken
		accessToken = JSON.parseObject(result.getResult(), AccessToken.class);
		return accessToken;
	}

	private HttpResponse doPost(String url, List<NameValuePair> params, String paramCharset) throws IOException {
		HttpPost httpPost = new HttpPost(url);

		if (null == paramCharset || paramCharset.trim().length() == 0) {
			paramCharset = "UTF-8";
		}
		
		if (null != params) {

			httpPost.setEntity(new UrlEncodedFormEntity(params, paramCharset));
		}
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}
	
	private HttpRequestResult doPostQuietly(String url, List<NameValuePair> params, String paramCharset) {
		HttpRequestResult httpRequestResult = new HttpRequestResult();
		
		try {
			HttpResponse httpResponse = doPost(url, params, paramCharset);
			httpRequestResult.setSuccess(true);
			httpRequestResult.setResult(EntityUtils.toString(httpResponse.getEntity(), paramCharset));
		} catch (IOException e) {
			e.printStackTrace();
			httpRequestResult.setSuccess(false);
			httpRequestResult.setResult(e.toString());
		}
		
		return httpRequestResult;
	}
	
	private HttpRequestResult doPostQuietly(String url, List<NameValuePair> params) {
		return doPostQuietly(url, params, null);
	}

	/**
	 * 发送单条消息
	 * 
	 * @param message  消息封包
	 */
	public SendMessageResult sendSingleMessage(MessageContent messageContent, PersonMessageReceiver messageReceiver) {
		return sendSingleMessage(messageContent, messageReceiver, PersonMessageReceiverIdType.LOGIN_ID);
	}
	
	/**
	 * 发送单条消息
	 * 
	 * @param messageContent 消息封包
	 * @param messageReceiver 接收者
	 * @param personMessageReceiverIdType 接收者id类型，参考PersonMessageReceiverIdType
	 * @return
	 */
	public SendMessageResult sendSingleMessage(MessageContent messageContent, PersonMessageReceiver messageReceiver, int personMessageReceiverIdType) {
		Guard.guardReqiredObject(messageContent, "message content must be set value.");
		guardMessageReceiver(messageReceiver);

		List<PersonMessageReceiver> messageReceivers = new ArrayList<PersonMessageReceiver>();
		messageReceivers.add(messageReceiver);
		MultiMessageReceiver multiMessageReceiver = new MultiMessageReceiver(messageReceivers, personMessageReceiverIdType);
		
		return sendMultiMessage(messageContent, multiMessageReceiver);
	}

	/**
	 * 发送多条消息
	 * @param messageContent   消息封包
	 * @param messageReceivers 消息接收者
	 */
	public SendMessageResult sendMultiMessage(MessageContent messageContent, MultiMessageReceiver messageReceivers) {
		SendMessageResult result = trySendMessage(messageContent, messageReceivers);
		if (result.isSuccess()) {
			return result;
		}
		
		// 当如果发现是因为token过期而发送不成功时，尝试再重试一次
		if (!result.isSuccess()&&null != result.getError()&&result.getError().toLowerCase().contains(INVALID_ACCESS_TOKEN)) {
			// 重置AccessToken，便于trySendMessage时，可以重新问消息服务要新的Token
			this.accessToken = null;
			result = trySendMessage(messageContent, messageReceivers);
		}
		
		return result;
	}
	
    /**
     * 尝试给多个人发消息
     * @param messageContent   消息封包
	 * @param messageReceivers 消息接收者
     */
	protected SendMessageResult trySendMessage(MessageContent messageContent, MultiMessageReceiver messageReceivers) {
		Message message = new Message();
		message.setType(messageContent.getType());
		message.setToDeviceTypes(messageContent.getToDeviceTypes());
		message.setContent(messageContent);
		
		// 获取token，检查token的有效性
		AccessToken token = getAccessToken();
		if (!token.isSuccess()) {
			return new SendMessageResult(false, token.getError());
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token",token.getAccessToken()));
		params.add(new BasicNameValuePair("message", message.toJson()));
		
		if (messageReceivers.getIdType() == PersonMessageReceiverIdType.LOGIN_ID) {
			params.add(new BasicNameValuePair("loginIds", messageReceivers.getToId()));
		} else if (messageReceivers.getIdType() == PersonMessageReceiverIdType.USER_ID) {
			params.add(new BasicNameValuePair("userIds", messageReceivers.getToId()));
		}
		
		params.add(new BasicNameValuePair("userNames", messageReceivers.getToName()));
		params.add(new BasicNameValuePair("id_type", String.valueOf(messageReceivers.getIdType())));
		params.add(new BasicNameValuePair("msgIds", makeMessageIds(messageReceivers.getLength())));

		HttpRequestResult requestResult = doPostQuietly(messageSendServiceUrl, params);
		
		if (!requestResult.isSuccess()) {
			return new SendMessageResult(false, requestResult.getResult());
		}
		
		return JSON.parseObject(requestResult.getResult(), SendMessageResult.class);
	}
	
	private String makeMessageIds(int messageNumber) {
		StringBuilder messageIds = new StringBuilder();
		for (int i = 0; i < messageNumber; i++) {
			messageIds.append(",").append(UUID.randomUUID().toString());
		}
		return messageIds.substring(1);
	}

	private void guardMessageReceiver(MessageReceiver messageReceiver) {
		Guard.guardReqiredObject(messageReceiver, "message receiver must be set value.");
		Guard.guardReqiredString(messageReceiver.getToId(), "message receiver id must be set value.");
		Guard.guardReqiredString(messageReceiver.getToName(), "message receiver name must be set value.");
	}
}