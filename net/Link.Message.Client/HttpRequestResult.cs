namespace Link.Message.Client
{


	/// <summary>
    /// Http请求返回的结果
	/// </summary>
	public class HttpRequestResult
	{
        public bool Success  {set; get;}
		public string Result {set;get;}

		public HttpRequestResult()
		{

		}

		public HttpRequestResult(bool success, string result)
		{
			Success = success;
			Result = result;
		}
	}
}