using Link.Message.Client.utils;
using Newtonsoft.Json;

namespace Link.Message.Client.content
{
	/// <summary>
    /// ���ı���Ϣ����
	/// </summary>
	[JsonConverter(typeof(StringOrObjectConverter))]
	public class TextMessageContent : MessageContent, IStringOrObjectSerializable
	{
		public string Text { set; get; }

		public TextMessageContent(string text)
		{
			Guard.GuardReqiredString(text, "TextMessageContent's content is required.");

			Text = text;
			Type = MessageType.Text;
		}

	    public override string ToString()
	    {
	        return Text;
	    }
	}

}