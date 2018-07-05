package link.message.client.content.complex.linear;

import link.message.client.content.StringContent;
import link.message.client.utils.Guard;

/**
 * Hint类型的线性消息内容项
 * 
 * @author zhong_t
 */

public class TextLinearComplexMessageContentItem extends TitleLinearComplexMessageContentItem {
	public TextLinearComplexMessageContentItem(String hint) {
		Guard.guardReqiredString(hint, "hint must be set value.");
		
		this.content = new StringContent(hint);
		this.displayType = LinearComplexMessageContentType.TEXT;
	}
}