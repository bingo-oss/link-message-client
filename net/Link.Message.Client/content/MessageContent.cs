using Newtonsoft.Json;

namespace Link.Message.Client.content
{
	/// <summary>
	/// 消息详细内容
	/// </summary>
	public abstract class MessageContent
	{
		// 消息类型
        [JsonIgnore]
        public virtual MessageType Type { get; set; }
	}

}