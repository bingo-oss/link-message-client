namespace Link.Message.Client.messager
{

	/// <summary>
    /// 个人消息接受者
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