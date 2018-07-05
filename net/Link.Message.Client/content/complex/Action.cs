using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex
{
	/// <summary>
	/// 点击二级消息体或者消息体里的消息项发生的事情
	/// </summary>
	public class Action
	{
	    private ActionType _actionType = ActionType.None;
	    public string ActionParam { set; get; }

	    protected internal Action()
		{

		}

		private Action(ActionType actionType, string actionParam)
		{
			_actionType = actionType;
			ActionParam = actionParam;
		}

		public static Action InstanceAsDoNothing()
		{
		     return new Action {ActionType = ActionType.None};
		}

		public static Action InstanceAsOpenUrl(string url)
		{
			Guard.GuardReqiredString(url, "url must be set value.");

			if (!url.Trim().ToLower().StartsWith("http:") && !url.Trim().ToLower().StartsWith("https:"))
			{
				Guard.ThrowIllegalArgumentException("url must start with http or https.");
			}

			return new Action(ActionType.Url, url);
		}

		public static Action InstanceAsOpenHtml(string html)
		{
			Guard.GuardReqiredString(html, "html must be set value.");

			return new Action(ActionType.Html, html);
		}

		public static Action InstanceAsOpenNative(string nativeCommandAndParams)
		{
			Guard.GuardReqiredString(nativeCommandAndParams, "nativeCommandAndParams must be set value.");

			return new Action(ActionType.Native, nativeCommandAndParams);
		}

		public virtual ActionType ActionType
		{
			get
			{
				return _actionType;
			}
			set
			{
				_actionType = value;
			}
		}

	}
}