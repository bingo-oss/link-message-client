namespace Link.Message.Client.messager
{

	/// <summary>
    /// ��Ϣ������
	/// </summary>
	public class MessageReceiver
	{
		public string ToId { set; get; }
		public string ToName { set; get; }
		public virtual MessageSendOrReceiverType ToType { set; get; }
		public string ToCompany { set; get; }
	}

}