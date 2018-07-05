package link.message.client.content.complex.linear;

import link.message.client.content.StringContent;
import link.message.client.utils.Guard;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 标题类型的线性消息内容项
 * 
 * @author zhongt
 *
 */
public class TitleLinearComplexMessageContentItem extends LinearComplexMessageContentItem {
	@JSONField(name="bgcolor")
	protected String  bgColor;
	@JSONField(name="font_color")
	protected String  fontColor;
	@JSONField(name="font_bold")
	protected boolean fontBold;
	@JSONField(name="border_color")
	protected String  borderColor;
	
	protected TitleLinearComplexMessageContentItem() {
		
	}
	
	public TitleLinearComplexMessageContentItem(String title) {
		Guard.guardReqiredString(title, "title must be set value.");
		
		this.content     = new StringContent(title);
		this.displayType = LinearComplexMessageContentType.TITLE;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public boolean isFontBold() {
		return fontBold;
	}

	public void setFontBold(boolean fontBold) {
		this.fontBold = fontBold;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

}