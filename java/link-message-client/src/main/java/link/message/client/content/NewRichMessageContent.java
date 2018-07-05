package link.message.client.content;

import link.message.client.StringOrObjectSerializable;

/**
 * 新文本消息的内容
 * 
 * @author zhongt
 *
 */
public class NewRichMessageContent implements StringOrObjectSerializable {

	protected String title;
	protected String brief;
	protected String more = "点击查看全文";

	public NewRichMessageContent() {
		
	}
	
	public NewRichMessageContent(String title, String brief) {
		this.title = title;
		this.brief = brief;
	}
	
	public NewRichMessageContent(String title, String brief, String more) {
		this(title, brief);
		this.more  = more;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

}
