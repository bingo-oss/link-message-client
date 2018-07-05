namespace Link.Message.Client.content
{
	/// <summary>
	/// 视频消息内容
	/// </summary>
	public class VideoMessageContent : FileMessageContent
	{

		public VideoMessageContent(string downloadUrl, int size, string fileName, string extension) : base(downloadUrl, size, fileName, extension)
		{
		}

		public VideoMessageContent(string downloadUrl, int size, string fullFileName) : base(downloadUrl, size, fullFileName)
		{
		}
	}

}