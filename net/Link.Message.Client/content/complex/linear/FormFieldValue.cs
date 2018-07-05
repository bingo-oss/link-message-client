namespace Link.Message.Client.content.complex.linear
{

	/// <summary>
    /// ���ֶζ�Ӧ��ֵ��Ϣ
	/// </summary>
	public class FormFieldValue
	{
		protected internal string key;
		protected internal string value;

		public FormFieldValue(string key, string value)
		{
			this.key = key;
			this.value = value;
		}

		public virtual string Key
		{
			get
			{
				return key;
			}
			set
			{
				key = value;
			}
		}


		public virtual string Value
		{
			get
			{
				return value;
			}
			set
			{
				this.value = value;
			}
		}

	}
}