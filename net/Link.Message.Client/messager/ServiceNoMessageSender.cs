namespace Link.Message.Client.messager
{

	/// <summary>
    /// �������Ϣ������
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