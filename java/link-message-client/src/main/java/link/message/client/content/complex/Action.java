package link.message.client.content.complex;

import link.message.client.utils.Guard;

/**
 * 点击二级消息体或者消息体里的消息项发生的事情
 * 
 * @author zhongt
 *
 */
public class Action {
	protected int    actionType = ActionType.NONE;
	protected String actionParam;
	
	protected Action() {
		
	}

	private Action(int actionType, String actionParam) {
		this.actionType  = actionType;
		this.actionParam = actionParam;
	}
	
	public static Action instanceAsDoNothing() {
		Action action = new Action();
		action.setActionType(ActionType.NONE);
		return action;
	}
	
	public static Action instanceAsOpenUrl(String url) {
		Guard.guardReqiredString(url, "url must be set value.");
		
		if (!url.trim().toLowerCase().startsWith("http:") && !url.trim().toLowerCase().startsWith("https:")) {
			Guard.throwIllegalArgumentException("url must start with http or https.");
		}
		
		return new Action(ActionType.URL, url);
	}
	
	public static Action instanceAsOpenHtml(String html) {
		Guard.guardReqiredString(html, "html must be set value.");
		
		return new Action(ActionType.HTML, html);	
	}
	
	public static Action instanceAsOpenNative(String nativeCommandAndParams) {
		Guard.guardReqiredString(nativeCommandAndParams, "nativeCommandAndParams must be set value.");
		
		return new Action(ActionType.NATIVE, nativeCommandAndParams);
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