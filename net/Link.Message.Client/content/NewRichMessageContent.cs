namespace Link.Message.Client.content
{
	/// <summary>
	/// 新文本消息的内容
	/// </summary>
	public class NewRichMessageContent : IStringOrObjectSerializable
	{

	    public string Title { set; get; }
		public string Brief { set; get; }
		public string More  { set; get; }

		public NewRichMessageContent()
		{
            More = "点击查看全文";
		}

	    public NewRichMessageContent(string title, string brief): this()
		{
			this.Title = title;
			this.Brief = brief;
		}

		public NewRichMessageContent(string title, string brief, string more) : this(title, brief)
		{
			this.More = more;
		}
	}

}