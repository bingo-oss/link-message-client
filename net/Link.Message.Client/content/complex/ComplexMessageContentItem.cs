using Link.Message.Client.utils;
using Newtonsoft.Json;

namespace Link.Message.Client.content.complex
{
	/// <summary>
	/// 复杂消息内容的组成项
	/// </summary>
	public class ComplexMessageContentItem
	{
		public IStringOrObjectSerializable Content { get; set; }
        [JsonProperty("img")]
        public string Img { get; set; }
        [JsonProperty("action_type")]
	    public ActionType ActionType { get; set; }
        [JsonProperty("action_param")]
	    public string ActionParam { get; set; }

		public ComplexMessageContentItem()
		{
		    ActionType = ActionType.None;
		}

		public ComplexMessageContentItem(IStringOrObjectSerializable content)
		{
			Guard.GuardReqiredObject(content, "content must be set value.");

			Content = content;
		}

		public ComplexMessageContentItem(string content)
		{
			Guard.GuardReqiredString(content, "content must be set value.");

			Content = new StringContent(content);
		}

		public ComplexMessageContentItem(string content, Action action) : this(content)
		{
			Guard.GuardReqiredObject(action, "action object must be set value.");

			ActionType = action.ActionType;
			ActionParam = action.ActionParam;
		}

		public ComplexMessageContentItem(IStringOrObjectSerializable content, Action action) : this(content)
		{

			Guard.GuardReqiredObject(action, "action object must be set value.");

			ActionType = action.ActionType;
			ActionParam = action.ActionParam;
		}

		public ComplexMessageContentItem(IStringOrObjectSerializable content, string image, Action action) : this(content, action)
		{
            Img = image;
		}

		public ComplexMessageContentItem(string content, string image, Action action) : this(new StringContent(content), action)
		{
			Img = image;
		}
	}
}