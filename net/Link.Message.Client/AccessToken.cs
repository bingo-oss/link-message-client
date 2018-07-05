using System;
using Newtonsoft.Json;

namespace Link.Message.Client
{
	/// <summary>
    ///  服务号发消息预先取得的访问令牌
	/// </summary>
	public class AccessToken
	{
        public bool Success { get; set; }
        [JsonProperty(propertyName:"Access_Token")]
        public string Token { get; set; }
        public DateTime Expire { get; set; }
        public string Error { get; set; }

		public AccessToken()
		{

		}

		public AccessToken(bool success, string error)
		{
			Success = success;
			Error = error;
		}
	}
}