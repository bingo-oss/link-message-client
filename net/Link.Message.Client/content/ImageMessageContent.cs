namespace Link.Message.Client.content
{

	/// <summary>
	/// 图片消息内容
	/// </summary>
	public class ImageMessageContent : FileMessageContent
	{

		public ImageMessageContent(string downloadUrl, int size, string fullFileName) : base(downloadUrl, size, fullFileName)
		{
		}

		public ImageMessageContent(string downloadUrl, int size, string fileName, string extension) : base(downloadUrl, size, fileName, extension)
		{
		}
	}
}