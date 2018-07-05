using System;
using System.CodeDom;
using Newtonsoft.Json;

namespace Link.Message.Client.content
{

	/// <summary>
	/// 直接把字符串作为content
	/// </summary>
	[JsonConverter(typeof(StringOrObjectConverter))]
	public class StringContent : IStringOrObjectSerializable
	{
		public string Text { set; get; }

	    public StringContent(String content)
	    {
	        Text = content;
	    }

	    public override string ToString()
	    {
	        return Text;
	    }
	}
}