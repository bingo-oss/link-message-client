package link.message.client.content.complex.linear;

import link.message.client.utils.Guard;


/**
 * 表单类型的线性消息内容项
 * 
 * @author zhongt
 *
 */
public class FormLinearComplexMessageContentItem extends LinearComplexMessageContentItem {
	public FormLinearComplexMessageContentItem(FormContent formContent) {
		Guard.guardReqiredObject(formContent,            "formContent must be set value.");
		Guard.guardReqiredObject(formContent.getKey(),   "formContent key must be set value.");
		Guard.guardReqiredObject(formContent.getValue(), "formContent value must be set value.");
		
		if (formContent.getKey().getWidth() + formContent.getValue().getWidth() != 100) {
			Guard.throwIllegalArgumentException("formContent key's width and value's width must total 100.");
		}
		
		this.displayType = LinearComplexMessageContentType.FORM;
		this.content     = formContent;
	}
}