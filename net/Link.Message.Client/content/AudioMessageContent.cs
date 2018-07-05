namespace Link.Message.Client.content
{

	/// <summary>
	/// 音频消息内容
	/// </summary>
	public class AudioMessageContent : FileMessageContent
	{

		public AudioMessageContent(string downloadUrl, int size, string fileName, string extension) : base(downloadUrl, size, fileName, extension)
		{
		}

		public AudioMessageContent(string downloadUrl, int size, string fullFileName) : base(downloadUrl, size, fullFileName)
		{
		}
	}

}