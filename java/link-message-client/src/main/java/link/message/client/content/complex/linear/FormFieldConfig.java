package link.message.client.content.complex.linear;

import link.message.client.utils.Guard;

/**
 * Form类型线性消息内容项的表单配置
 * 
 * @author zhongt
 *
 */
public class FormFieldConfig {
	protected int width       = 75;
	protected Alignment align = Alignment.left;

	public FormFieldConfig() {
		
	}
	
	public FormFieldConfig(int width, Alignment align) {
		Guard.guardReqiredObject(width, "width must be set value.");
		this.width = width;
		this.align = align;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Alignment getAlign() {
		return align;
	}

	public void setAlign(Alignment align) {
		this.align = align;
	}

}
