namespace Link.Message.Client.messager
{

	/// <summary>
    /// ������Ϣ������
	/// </summary>
	public class PersonMessageReceiver : MessageReceiver
	{

		public PersonMessageReceiver()
		{

		}

		public PersonMessageReceiver(string id, string name)
		{
			ToId = id;
			ToName = name;
		}

		public override MessageSendOrReceiverType ToType
		{
			get
			{
				return MessageSendOrReceiverType.Person;
			}
			set
			{
				base.ToType = MessageSendOrReceiverType.Person;
			}
		}

	}
}