namespace Link.Message.Client.content
{

	/// <summary>
	/// 语音消息内容
	/// </summary>
	public class VoiceMessageContent : FileMessageContent
	{

		public VoiceMessageContent(string downloadUrl, int size, string fileName, string extension) : base(downloadUrl, size, fileName, extension)
		{
		}

		public VoiceMessageContent(string downloadUrl, int size, string fullFileName) : base(downloadUrl, size, fullFileName)
		{
		}
	}

}