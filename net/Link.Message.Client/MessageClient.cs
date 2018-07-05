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
    /// ��Ϣ����Http�ͻ���
    /// </summary>
    public class MessageClient
    {
        private readonly WebClient _webClient;
        private const string Token = "token";
        private const string Send = "send";

        private readonly string _appId;
        private readonly string _secretKey;

        private readonly string _tokenGetServiceUrl;    // ��ȡtoken�ķ����ַ
        private readonly string _messageSendServiceUrl; // ������Ϣ�ķ����ַ

        private AccessToken _accessToken;

        /// <param name="embServiceUrl">emb����ĵ�ַ</param>
        /// <param name="appId">�����Id</param>
        /// <param name="secretKey">�������Կ</param>
        /// <param name="webClient">����Զ��http����Ŀͻ��˶���Ϊ�������Ĭ�ϵ�httpClient</param>
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

        /// <param name="embServiceUrl">emb����ĵ�ַ</param>
        /// <param name="appId">�����Id</param>
        /// <param name="secretKey">�������Կ</param>
		public MessageClient(string embServiceUrl, string appId, string secretKey) : this(embServiceUrl, appId, secretKey, null)
        {
        }

        public virtual AccessToken GetAccessToken()
        {
            // ���token����
            if (null != _accessToken && _accessToken.Success && _accessToken.Expire > new DateTime())
            {
                return _accessToken;
            }

            HttpRequestResult result = DoGet(String.Format(_tokenGetServiceUrl + "?grant_type={0}&appid={1}&secret={2}", "client_credential", _appId, _secretKey));

            // �������rest����������ˣ���ô��ֱ��accessToken
            if (!result.Success)
            {
                _accessToken = new AccessToken(result.Success, result.Result);
                return _accessToken;
            }

            // ����accessToken
            _accessToken = JsonConvert.DeserializeObject<AccessToken>(result.Result);
            return _accessToken;
        }

        /// <summary>  
        /// ����POST��ʽ��HTTP����  
        /// </summary>  
        /// <param name="url">�����URL</param>  
        /// <param name="parameters">��ͬ����POST�Ĳ������Ƽ�����ֵ�ֵ�</param>  
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
        /// ���͵�����Ϣ
        /// </summary>
        /// <param name="messageContent">��Ϣ���</param>
        /// <param name="messageReceiver">��Ϣ��</param>
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
        /// ���͵�����Ϣ
        /// </summary>
        /// <param name="messageContent">��Ϣ���</param>
        /// <param name="messageReceiver">������</param>
        /// <param name="personMessageReceiverIdType">������id���ͣ��ο�PersonMessageReceiverIdType</param>
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
        /// ���Ͷ�����Ϣ</summary>
        /// <param name="messageContent">��Ϣ���</param>
        /// <param name="messageReceivers">��Ϣ������</param>
        public virtual SendMessageResult SendMultiMessage(MessageContent messageContent, MultiMessageReceiver messageReceivers)
        {
            Message message = new Message { Type = messageContent.Type, Content = messageContent };

            // ��ȡtoken�����token����Ч��
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