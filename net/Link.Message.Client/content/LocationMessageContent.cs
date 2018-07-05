namespace Link.Message.Client.content
{

	/// <summary>
	/// 地理位置信息内容
	/// </summary>
	public class LocationMessageContent : MessageContent
	{
		public string Addr { set; get; }
        public string Longitude { set; get; }
        public string Latitude { set; get; }
	}

}