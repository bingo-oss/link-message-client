package link.message.client.content.complex.linear;

import link.message.client.content.complex.ComplexMessageContentItem;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 负责消息里的线性消息项
 * 
 * @author zhongt
 *
 */
public class LinearComplexMessageContentItem extends ComplexMessageContentItem {
	@JSONField(name="display_type")
	protected int displayType;

	public int getDisplayType() {
		return displayType;
	}

	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}

}