package link.message.client.content.complex;

import link.message.client.StringOrObjectSerializable;
import link.message.client.content.StringContent;
import link.message.client.utils.Guard;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 复杂消息内容的组成项
 * 
 * @author zhongt
 *
 */
public class ComplexMessageContentItem {
	protected StringOrObjectSerializable content;
	private String                     img;
	@JSONField(name="action_type")
	private int                        actionType = ActionType.NONE;
	@JSONField(name="action_param")
	private String                     actionParam;

	public ComplexMessageContentItem() {
		
	}
	
	public ComplexMessageContentItem(StringOrObjectSerializable content) {
		Guard.guardReqiredObject(content, "content must be set value.");
		
		this.content = content;
	}
	
	public ComplexMessageContentItem(String content) {
		Guard.guardReqiredString(content, "content must be set value.");
		
		this.content = new StringContent(content);
	}
	
	public ComplexMessageContentItem(String content, Action action) {
		this(content);
		
		Guard.guardReqiredObject(action,  "action object must be set value.");
		
		this.actionType  = action.getActionType();
		this.actionParam = action.getActionParam();
	}
	
	public ComplexMessageContentItem(StringOrObjectSerializable content, Action action) {
		this(content);
		
		Guard.guardReqiredObject(action, "action object must be set value.");
		
		this.actionType  = action.getActionType();
		this.actionParam = action.getActionParam();
	}
	
	public ComplexMessageContentItem(StringOrObjectSerializable content, String image, Action action) {
		this(content, action);
		
		this.img = image;
	}
	
	public ComplexMessageContentItem(String content, String image, Action action) {
		this(new StringContent(content), action);
		
		this.img = image;
	}
	
	public StringOrObjectSerializable getContent() {
		return content;
	}

	public void setContent(StringOrObjectSerializable content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getActionParam() {
		return actionParam;
	}

	public void setActionParam(String actionParam) {
		this.actionParam = actionParam;
	}
}