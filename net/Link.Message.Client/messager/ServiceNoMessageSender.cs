namespace Link.Message.Client.messager
{

	/// <summary>
    /// 服务号消息发送者
	/// </summary>
	public class ServiceNoMessageSender : MessageSender
	{
		public ServiceNoMessageSender(string fromId, string fromName, string fromCompany)
		{
			base.FromType = MessageSendOrReceiverType.ServiceNo;
			FromId = fromId;
			FromName = fromName;
			FromCompany = fromCompany;
		}

		public override MessageSendOrReceiverType FromType
		{
			set
			{
				base.FromType = MessageSendOrReceiverType.ServiceNo;
			}
		}
	}
}