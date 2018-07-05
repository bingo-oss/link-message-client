using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex.linear
{
	/// <summary>
	/// Hint类型的线性消息内容项
	/// </summary>
	public class TextLinearComplexMessageContentItem : TitleLinearComplexMessageContentItem
	{
		public TextLinearComplexMessageContentItem(string hint)
		{
			Guard.GuardReqiredString(hint, "hint must be set value.");

			Content = new StringContent(hint);
			DisplayType = LinearComplexMessageContentType.Text;
		}
	}
}