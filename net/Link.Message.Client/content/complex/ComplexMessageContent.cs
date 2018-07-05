using System.Collections.Generic;
using System.Runtime.Serialization;
using Newtonsoft.Json;

namespace Link.Message.Client.content.complex
{
	/// <summary>
	/// 复杂消息内容
	/// </summary>
	public class ComplexMessageContent : MessageContent
	{
		public string              Brief { set; get; }
        private ComplexMessageType _complexType = ComplexMessageType.Text;
        private InvokeType         _invokeType = InvokeType.InvokeChatWindow;

		private IList<ComplexMessageContentItem> _contents = new List<ComplexMessageContentItem>();

		public ComplexMessageContent()
		{
			Type = MessageType.Complex;
		}

		public ComplexMessageContent(string brief) : this()
		{
			Brief = brief;
		}

        public ComplexMessageContent(string brief, ComplexMessageType complexType)
            : this(brief)
		{
			_complexType = complexType;
		}

        public ComplexMessageContent(string brief, ComplexMessageType complexType, InvokeType invokeType)
            : this(brief, complexType)
		{
			_invokeType = invokeType;
		}

		public static ComplexMessageContent InstanceAsTextMessageContent(string brief, string content)
		{
			ComplexMessageContent textMessageContent = new ComplexMessageContent(brief, ComplexMessageType.Text);
			textMessageContent.AddMessageContentItem(new ComplexMessageContentItem(new StringContent(content)));
			return textMessageContent;
		}

		public static ComplexMessageContent InstanceAsTextMessageContent(string brief, string content, Action action)
		{
			ComplexMessageContent textMessageContent = new ComplexMessageContent(brief, ComplexMessageType.Text);
			textMessageContent.AddMessageContentItem(new ComplexMessageContentItem(new StringContent(content), action));
			return textMessageContent;
		}

        [JsonProperty("type")]
        public virtual ComplexMessageType ComplexType
		{
			get
			{
				return _complexType;
			}
			set
			{
				_complexType = value;
			}
		}


        public virtual InvokeType InvokeType
		{
			get
			{
				return _invokeType;
			}
			set
			{
				_invokeType = value;
			}
		}


		public virtual IList<ComplexMessageContentItem> Contents
		{
			get
			{
				return _contents;
			}
			set
			{
				_contents = value;
			}
		}


		public virtual ComplexMessageContent AddMessageContentItem(ComplexMessageContentItem item)
		{
			_contents.Add(item);
			return this;
		}
	}

}