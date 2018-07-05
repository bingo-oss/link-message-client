using Link.Message.Client.content;
using Link.Message.Client.messager;
using Newtonsoft.Json;

namespace Link.Message.Client
{
	/// <summary>
	/// Emb消息封包
	/// </summary>
	public class Message
	{
        [JsonProperty(propertyName: "msg_id")]
        public string Id { get; set; }
        [JsonProperty(propertyName: "msg_type")]
        public MessageType Type { get; set; }
        [JsonConverter(typeof(StringOrObjectConverter))]
		public MessageContent Content { get; set; }
		public MessageSender From { get; set; }
		public MessageReceiver To { get; set; }
	}

}