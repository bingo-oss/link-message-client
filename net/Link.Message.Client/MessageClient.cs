using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net;
using System.Text;
using Link.Message.Client.content;
using Link.Message.Client.messager;
using Link.Message.Client.utils;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace Link.Message.Client
{
    /// <summary>
    /// 消息服务Http客户端
    /// </summary>
    public class MessageClient
    {
        private readonly WebClient _webClient;
        private const string Token = "token";
        private const string Send = "send";

        private readonly string _appId;
        private readonly string _secretKey;

        private readonly string _tokenGetServiceUrl;    // 获取token的服务地址
        private readonly string _messageSendServiceUrl; // 发送消息的服务地址

        private AccessToken _accessToken;

        /// <param name="embServiceUrl">emb服务的地址</param>
        /// <param name="appId">服务号Id</param>
        /// <param name="secretKey">服务号密钥</param>
        /// <param name="webClient">请求远程http服务的客户端对象，为空则采用默认的httpClient</param>
        public MessageClient(string embServiceUrl, string appId, string secretKey, WebClient webClient)
        {
            Guard.GuardReqiredString(embServiceUrl, "emb's service url is required.");
            Guard.GuardReqiredString(appId, "service no's id (appId) is required.");
            Guard.GuardReqiredString(secretKey, "service no's secret key is required.");

            var embServiceUrl1 = embServiceUrl.Trim().EndsWith("/") ? embServiceUrl.Trim() : embServiceUrl + "/";

            _appId = appId.Trim();
            _secretKey = secretKey.Trim();
            _webClient = webClient;

            _tokenGetServiceUrl = embServiceUrl1 + Token;
            _messageSendServiceUrl = embServiceUrl1 + Send;

            if (null == _webClient)
            {
                _webClient = DefaultHttpClient();
            }
        }

        private WebClient DefaultHttpClient()
        {
            return new WebClient();
        }

        /// <param name="embServiceUrl">emb服务的地址</param>
        /// <param name="appId">服务号Id</param>
        /// <param name="secretKey">服务号密钥</param>
		public MessageClient(string embServiceUrl, string appId, string secretKey) : this(embServiceUrl, appId, secretKey, null)
        {
        }

        public virtual AccessToken GetAccessToken()
        {
            // 检查token过期
            if (null != _accessToken && _accessToken.Success && _accessToken.Expire > new DateTime())
            {
                return _accessToken;
            }

            HttpRequestResult result = DoGet(String.Format(_tokenGetServiceUrl + "?grant_type={0}&appid={1}&secret={2}", "client_credential", _appId, _secretKey));

            // 如果调用rest服务出问题了，那么就直接accessToken
            if (!result.Success)
            {
                _accessToken = new AccessToken(result.Success, result.Result);
                return _accessToken;
            }

            // 缓存accessToken
            _accessToken = JsonConvert.DeserializeObject<AccessToken>(result.Result);
            return _accessToken;
        }

        /// <summary>  
        /// 创建POST方式的HTTP请求  
        /// </summary>  
        /// <param name="url">请求的URL</param>  
        /// <param name="parameters">随同请求POST的参数名称及参数值字典</param>  
        /// <returns></returns>  
        public HttpRequestResult DoPost(string url, NameValueCollection parameters)
        {
            HttpRequestResult httpRequestResult = new HttpRequestResult();

            try
            {
                _webClient.Headers.Add("Content-Type", "application/x-www-form-urlencoded");
                var responseBuffer = _webClient.UploadValues(_messageSendServiceUrl, "POST", parameters);
                httpRequestResult.Success = true;
                httpRequestResult.Result = Encoding.UTF8.GetString(responseBuffer);
            }
            catch (WebException e)
            {
                httpRequestResult.Success = false;
                httpRequestResult.Result = e.Message;
            }

            return httpRequestResult;
        }

        public HttpRequestResult DoGet(string url)
        {
            HttpRequestResult httpRequestResult = new HttpRequestResult();

            try
            {
                _webClient.Headers.Add("Content-Type", "application/x-www-form-urlencoded");
                var responseBuffer = _webClient.DownloadString(url);
                httpRequestResult.Success = true;
                httpRequestResult.Result = responseBuffer;
            }
            catch (WebException e)
            {
                httpRequestResult.Success = false;
                httpRequestResult.Result = e.Message;
            }

            return httpRequestResult;
        }

        /// <summary>
        /// 发送单条消息
        /// </summary>
        /// <param name="messageContent">消息封包</param>
        /// <param name="messageReceiver">消息接</param>
        public virtual SendMessageResult SendSingleMessage(MessageContent messageContent, PersonMessageReceiver messageReceiver)
        {
            Guard.GuardReqiredObject(messageContent, "message content must be set value.");
            GuardMessageReceiver(messageReceiver);

            IList<PersonMessageReceiver> messageReceivers = new List<PersonMessageReceiver>();
            messageReceivers.Add(messageReceiver);
            MultiMessageReceiver multiMessageReceiver = new MultiMessageReceiver(messageReceivers, PersonMessageReceiverIdType.LoginId);

            return SendMultiMessage(messageContent, multiMessageReceiver);
        }

        /// <summary>
        /// 发送单条消息
        /// </summary>
        /// <param name="messageContent">消息封包</param>
        /// <param name="messageReceiver">接收者</param>
        /// <param name="personMessageReceiverIdType">接收者id类型，参考PersonMessageReceiverIdType</param>
        public virtual SendMessageResult SendSingleMessage(MessageContent messageContent, PersonMessageReceiver messageReceiver, PersonMessageReceiverIdType personMessageReceiverIdType)
        {
            Guard.GuardReqiredObject(messageContent, "message content must be set value.");
            GuardMessageReceiver(messageReceiver);

            IList<PersonMessageReceiver> messageReceivers = new List<PersonMessageReceiver>();
            messageReceivers.Add(messageReceiver);
            MultiMessageReceiver multiMessageReceiver = new MultiMessageReceiver(messageReceivers, personMessageReceiverIdType);

            return SendMultiMessage(messageContent, multiMessageReceiver);
        }

        /// <summary>
        /// 发送多条消息</summary>
        /// <param name="messageContent">消息封包</param>
        /// <param name="messageReceivers">消息接收者</param>
        public virtual SendMessageResult SendMultiMessage(MessageContent messageContent, MultiMessageReceiver messageReceivers)
        {
            Message message = new Message { Type = messageContent.Type, Content = messageContent };

            // 获取token，检查token的有效性
            AccessToken token = GetAccessToken();
            if (!token.Success)
            {
                return new SendMessageResult(false, token.Error);
            }

            NameValueCollection parameters = new NameValueCollection();
            parameters.Add("access_token", token.Token);
            parameters.Add("message", SerializeMessage(message));

            if (messageReceivers.IdType == PersonMessageReceiverIdType.LoginId)
            {
                parameters.Add("loginIds", messageReceivers.ToId);
            }
            else if (messageReceivers.IdType == PersonMessageReceiverIdType.UserId)
            {
                parameters.Add("userIds", messageReceivers.ToId);
            }

            parameters.Add("userNames", messageReceivers.ToName);
            parameters.Add("id_type", ((int)messageReceivers.IdType).ToString());
            parameters.Add("msgIds", MakeMessageIds(messageReceivers.Length));

            HttpRequestResult requestResult = DoPost(_messageSendServiceUrl, parameters);

            if (!requestResult.Success)
            {
                return new SendMessageResult(false, requestResult.Result);
            }

            return JsonConvert.DeserializeObject<SendMessageResult>(requestResult.Result);
        }

        private string SerializeMessage(Message message)
        {
            return JsonConvert.SerializeObject(message, new JsonSerializerSettings
            {
                NullValueHandling = NullValueHandling.Ignore,
                ContractResolver = new CamelCasePropertyNamesContractResolver()
            });
        }

        private string MakeMessageIds(int messageNumber)
        {
            StringBuilder messageIds = new StringBuilder();
            for (int i = 0; i < messageNumber; i++)
            {
                messageIds.Append(",").Append(Guid.NewGuid().ToString());
            }
            return messageIds.ToString().Substring(1);
        }

        private void GuardMessageReceiver(MessageReceiver messageReceiver)
        {
            Guard.GuardReqiredObject(messageReceiver, "message receiver must be set value.");
            Guard.GuardReqiredString(messageReceiver.ToId, "message receiver id must be set value.");
            Guard.GuardReqiredString(messageReceiver.ToName, "message receiver name must be set value.");
        }
    }
}