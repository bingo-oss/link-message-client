namespace Link.Message.Client.content.complex.linear
{

	using Guard = utils.Guard;


	/// <summary>
	/// 表单类型的线性消息内容项
	/// </summary>
	public class FormLinearComplexMessageContentItem : LinearComplexMessageContentItem
	{
		public FormLinearComplexMessageContentItem(FormContent formContent)
		{
			Guard.GuardReqiredObject(formContent, "formContent must be set value.");
			Guard.GuardReqiredObject(formContent.Key, "formContent key must be set value.");
			Guard.GuardReqiredObject(formContent.Value, "formContent value must be set value.");

            if (formContent.Key.Width + formContent.Value.Width != 100)
			{
				Guard.ThrowIllegalArgumentException("formContent key's width and value's width must total 100.");
			}

			DisplayType = LinearComplexMessageContentType.Form;
			Content = formContent;
		}
	}
}