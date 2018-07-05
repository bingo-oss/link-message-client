namespace Link.Message.Client.content
{

	/// <summary>
	/// 调用类型
	/// </summary>
	public enum InvokeType
	{
        /// <summary>
        ///不调用（仅仅只在消息中心列表显示）
        /// </summary>
        DoNothing = 0,

        /// <summary>
        /// 一级调用（直接打开消息里，contents的第一个content中定义的action，目前只支持action_type为app、url和html，如果是none则无反应）
        /// </summary>
		InvokeCustomApp = 1,

        /// <summary>
        /// 二级调用（直接打开聊天窗口，展示聊天对话信息）
        /// </summary>
		InvokeChatWindow = 2
	}
}