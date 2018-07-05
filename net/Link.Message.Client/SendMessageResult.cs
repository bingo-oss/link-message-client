using Newtonsoft.Json;

namespace Link.Message.Client
{
	/// <summary>
	/// 消息发送的结果
	/// </summary>
	public class SendMessageResult
	{
		public bool   Success;
        [JsonProperty("err")]
		public string Error;

		public SendMessageResult()
		{

		}

		public SendMessageResult(bool success, string error)
		{
			Success = success;
			Error   = error;
		}
	}
}