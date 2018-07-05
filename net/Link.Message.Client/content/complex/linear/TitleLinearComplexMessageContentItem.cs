using Link.Message.Client.utils;
using Newtonsoft.Json;

namespace Link.Message.Client.content.complex.linear
{
	/// <summary>
	/// 标题类型的线性消息内容项
	/// </summary>
	public class TitleLinearComplexMessageContentItem : LinearComplexMessageContentItem
	{
		protected internal string bgColor;
//JAVA TO C# CONVERTER TODO TASK: Most Java annotations will not have direct .NET equivalent attributes:
//ORIGINAL LINE: @JSONField(name="font_color") protected String fontColor;
		protected internal string fontColor;
//JAVA TO C# CONVERTER TODO TASK: Most Java annotations will not have direct .NET equivalent attributes:
//ORIGINAL LINE: @JSONField(name="font_bold") protected boolean fontBold;
		protected internal bool fontBold;
//JAVA TO C# CONVERTER TODO TASK: Most Java annotations will not have direct .NET equivalent attributes:
//ORIGINAL LINE: @JSONField(name="border_color") protected String borderColor;
		protected internal string borderColor;

		protected internal TitleLinearComplexMessageContentItem()
		{

		}

		public TitleLinearComplexMessageContentItem(string title)
		{
			Guard.GuardReqiredString(title, "title must be set value.");

			Content = new StringContent(title);
			DisplayType = LinearComplexMessageContentType.Title;
		}

        [JsonProperty("bgcolor")]
		public virtual string BgColor
		{
			get
			{
				return bgColor;
			}
			set
			{
				bgColor = value;
			}
		}

        [JsonProperty("font_color")]
		public virtual string FontColor
		{
			get
			{
				return fontColor;
			}
			set
			{
				fontColor = value;
			}
		}

        [JsonProperty("font_bold")]
		public virtual bool FontBold
		{
			get
			{
				return fontBold;
			}
			set
			{
				fontBold = value;
			}
		}

        [JsonProperty("border_color")]
		public virtual string BorderColor
		{
			get
			{
				return borderColor;
			}
			set
			{
				borderColor = value;
			}
		}


	}
}