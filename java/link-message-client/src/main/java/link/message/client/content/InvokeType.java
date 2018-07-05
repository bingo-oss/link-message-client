package link.message.client.content;

/**
 * 调用类型
 * @author zhongt
 *
 */
public class InvokeType {
	
	/**不调用（仅仅只在消息中心列表显示）*/
	public static final int DO_NOTHING         = 0;
	/**一级调用（直接打开消息里，contents的第一个content中定义的action，目前只支持action_type为app、url和html，如果是none则无反应）*/
	public static final int INVOKE_CUSTOM_APP  = 1;
	/**二级调用（直接打开聊天窗口，展示聊天对话信息）*/
	public static final int INVOKE_CHAT_WINDOW = 2;
}