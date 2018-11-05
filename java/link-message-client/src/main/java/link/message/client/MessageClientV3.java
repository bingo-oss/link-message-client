package link.message.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import link.message.client.auth.AccessTokenProvider;
import link.message.client.auth.SsoAccessToken;
import link.message.client.content.MessageContent;
import link.message.client.messager.MultiMessageReceiver;
import link.message.client.messager.PersonMessageReceiverIdType;

/**
 * 消息服务Http客户端
 * 
 * @author zhongt
 *
 */
public class MessageClientV3 extends MessageClient {
	
	protected IMConfig config;
	protected AccessTokenProvider tp;
    protected SsoAccessToken at;

	/**
     * 创建一个{@link IMClient}对象，并使用授权码<code>code</code>获取一个at
     */
    public MessageClientV3(IMConfig config,AccessTokenProvider tp) {
        super(config.getSnoUrl());
        
    	this.config = config;
        this.tp = tp;
//        this.at = accessToken;
    }
    
	protected SsoAccessToken at(){
    	// 当刷新token的时候，sso有时候会返回500错误，改成获取新token
    	if(null == at || at.isExpired()){
            at = tp.obtainAccessTokenByClientCredentials();
        }
        return at;
    }
	
	/**
     * 尝试给多个人发消息
     * @param messageContent   消息封包
	 * @param messageReceivers 消息接收者
     */
	protected SendMessageResult trySendMessage(MessageContent messageContent, MultiMessageReceiver messageReceivers) {
		Message message = new Message();
		message.setType(messageContent.getType());
		message.setFrom(messageContent.getFrom());
		message.setToDeviceTypes(messageContent.getToDeviceTypes());
		message.setContent(messageContent);
		
		Map<String, String> headers = createHeaders();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("message", message.toJson()));
		
		if (messageReceivers.getIdType() == PersonMessageReceiverIdType.LOGIN_ID) {
			params.add(new BasicNameValuePair("loginIds", messageReceivers.getToId()));
		} else if (messageReceivers.getIdType() == PersonMessageReceiverIdType.USER_ID) {
			params.add(new BasicNameValuePair("userIds", messageReceivers.getToId()));
		}
		
		params.add(new BasicNameValuePair("userNames", messageReceivers.getToName()));
		params.add(new BasicNameValuePair("id_type", String.valueOf(messageReceivers.getIdType())));
		params.add(new BasicNameValuePair("msgIds", makeMessageIds(messageReceivers.getLength())));

		HttpRequestResult requestResult = doPostQuietly(messageSendServiceUrl, params, headers);
		
		if (!requestResult.isSuccess()) {
			return new SendMessageResult(false, requestResult.getResult());
		}
		
		return JSON.parseObject(requestResult.getResult(), SendMessageResult.class);
	}
	
	public Map<String, String> createHeaders(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + at().getAccessToken());
        return headers;
    }
}