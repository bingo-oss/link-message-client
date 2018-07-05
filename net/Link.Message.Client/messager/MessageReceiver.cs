namespace Link.Message.Client.messager
{

	/// <summary>
    /// 消息接收者
	/// </summary>
	public class MessageReceiver
	{
		public string ToId { set; get; }
		public string ToName { set; get; }
		public virtual MessageSendOrReceiverType ToType { set; get; }
		public string ToCompany { set; get; }
	}

}