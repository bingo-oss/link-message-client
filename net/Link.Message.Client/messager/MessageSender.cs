namespace Link.Message.Client.messager
{
	/// <summary>
    /// ��Ϣ������
	/// </summary>
	public class MessageSender
	{
		public string FromId { set; get; }
        public string FromName { set; get; }
        public virtual MessageSendOrReceiverType FromType { set; get; }
        public string FromCompany { set; get; }
	}
}