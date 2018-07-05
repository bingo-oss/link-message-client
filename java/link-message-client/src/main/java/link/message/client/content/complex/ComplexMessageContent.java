package link.message.client.content.complex;

import java.util.ArrayList;
import java.util.List;

import link.message.client.MessageType;
import link.message.client.content.InvokeType;
import link.message.client.content.MessageContent;
import link.message.client.content.StringContent;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 复杂消息内容
 * @author zhongt
 *
 */
public class ComplexMessageContent extends MessageContent {
	private String brief;
	@JSONField(name="type")
	private int    complexType = ComplexMessageType.TEXT;
	private int    invokeType  = InvokeType.INVOKE_CHAT_WINDOW;
	
	private List<ComplexMessageContentItem> contents = new ArrayList<ComplexMessageContentItem>();

	public ComplexMessageContent() {
		this.type = MessageType.COMPLEX.value();
	}
	
	public ComplexMessageContent(String brief) {
		this();
		this.brief = brief;
	}
	
	public ComplexMessageContent(String brief, int complexType) {
		this(brief);
		this.complexType  = complexType;
	}
	
	public ComplexMessageContent(String brief, int complexType, int invokeType) {
		this(brief, complexType);
		this.invokeType = invokeType;
	}
	
	public static ComplexMessageContent instanceAsTextMessageContent(String brief, String content) {
		ComplexMessageContent textMessageContent = new ComplexMessageContent(brief, ComplexMessageType.TEXT);
		textMessageContent.addMessageContentItem(new ComplexMessageContentItem(new StringContent(content)));
		return textMessageContent;
	}
	
	public static ComplexMessageContent instanceAsTextMessageContent(String brief, String content, Action action) {
		ComplexMessageContent textMessageContent = new ComplexMessageContent(brief, ComplexMessageType.TEXT);
		textMessageContent.addMessageContentItem(new ComplexMessageContentItem(new StringContent(content), action));
		return textMessageContent;
	}
	
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getComplexType() {
		return complexType;
	}

	public void setComplexType(int complexType) {
		this.complexType = complexType;
	}

	public int getInvokeType() {
		return invokeType;
	}

	public void setInvokeType(int invokeType) {
		this.invokeType = invokeType;
	}

	public List<ComplexMessageContentItem> getContents() {
		return contents;
	}

	public void setContents(List<ComplexMessageContentItem> contents) {
		this.contents = contents;
	}
	
	public ComplexMessageContent addMessageContentItem(ComplexMessageContentItem item) {
		this.contents.add(item);
		return this;
	}
}
