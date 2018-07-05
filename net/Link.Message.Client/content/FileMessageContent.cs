using System;
using Link.Message.Client.utils;
using Newtonsoft.Json;

namespace Link.Message.Client.content
{
	

	/// <summary>
	/// 文件消息内容
	/// </summary>
	public class FileMessageContent : MessageContent
	{
        [JsonProperty("file_name")]
		public string FileName;
		public int    Size;
        [JsonProperty("download_url")]
		public string DownloadUrl;
		public string Extension;

		public FileMessageContent()
		{

		}

		public FileMessageContent(string downloadUrl, int size, string fileName, string extension) : this(downloadUrl, size, fileName + "." + extension)
		{
		}

		public FileMessageContent(string downloadUrl, int size, string fullFileName)
		{
			Guard.GuardReqiredString(downloadUrl, "downloadUrl must be set value.");
			Guard.GuardReqiredString(fullFileName, "fullFileName must be set value.");
			Guard.GuardReqiredObject(size, "size must be set value.");

			DownloadUrl = downloadUrl;
			Size = size;

			int position = fullFileName.LastIndexOf(".", StringComparison.Ordinal);
			Extension = position <= 0 ? null : fullFileName.Trim().Substring(position);
			FileName = fullFileName.Trim().Substring(0, position);

			Guard.GuardReqiredObject(size, "fullFileName must include extension name.");
			Guard.GuardReqiredObject(size, "fullFileName must include file name.");
		}
	}

}