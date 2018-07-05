namespace Link.Message.Client.messager
{
	/// <summary>
    /// 消息发送者
	/// </summary>
	public class MessageSender
	{
		public string FromId { set; get; }
        public string FromName { set; get; }
        public virtual MessageSendOrReceiverType FromType { set; get; }
        public string FromCompany { set; get; }
	}
}