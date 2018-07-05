using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex
{
	public class Param
	{
	    public string Key { get;set; }
	    public string Value { get; set; }

		protected internal Param()
		{

		}

		public Param(string key, string value)
		{
			Guard.GuardReqiredString(key, "key must be set value.");
			Guard.GuardReqiredString(value, "key must be set value.");

			this.Key = key;
			this.Value = value;
		}
	}
}