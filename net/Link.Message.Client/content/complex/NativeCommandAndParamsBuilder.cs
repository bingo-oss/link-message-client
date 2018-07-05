using System.Text;
using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex
{
	/// <summary>
    /// NativeAction所需的actionParam参数构建器
	/// actionParam的具体格式是：
    ///[command指令]
    ///param1=paramValue1
    ///param2=paramValue2
    ///@author zhong_t
	/// </summary>
	public class NativeCommandAndParamsBuilder
	{
		protected internal StringBuilder ActionParams = new StringBuilder();

		public NativeCommandAndParamsBuilder(string command)
		{
			Guard.GuardReqiredString(command, "command must be set value.");

			ActionParams.Append("[").Append(command).Append("]");
		}

		public virtual NativeCommandAndParamsBuilder Append(string key, string value)
		{

			ActionParams.Append("\n").Append(key).Append("=").Append(value);

			return this;
		}

		public override string ToString()
		{
			return ActionParams.ToString();
		}

		/// <summary>
        /// 构建一个打开bingotouch本地页面的所需action参数 </summary>
        /// <param name="bingoTouchAppCode"> 应用编码 </param>
        /// <param name="localPageUrl">      打开的页面URL，相对路径 </param>
        /// <param name="params">            配套的键值对 </param>
        /// <returns> 组装好的actionParam </returns>
		public static string BuildAsOpenBingoTouchLocalAppPage(string bingoTouchAppCode, string localPageUrl, params Param[] @params)
		{
		    Param[] paramsNew = null;

		    int startIndex = 0;
		    if (null != @params) {
		        paramsNew = new Param[@params.Length + 2];
		        for (int i = 0; i < @params.Length; ++i) {
		            paramsNew[i] = @params[i];
		        }
		        startIndex = @params.Length;
		    } else {
		        paramsNew = new Param[2];
		    }
		    paramsNew[startIndex]   = new Param("appCode", bingoTouchAppCode);
		    paramsNew[startIndex + 1] = new Param("appUrl", localPageUrl);
				
		    return BuildAsOpenBingoTouchLocalAppPage(bingoTouchAppCode, localPageUrl, "OpenApp", paramsNew);
		}

        /// <summary>
        /// 构建一个打开bingotouch本地页面的所需action参数 </summary>
        /// <param name="bingoTouchAppCode"> 应用编码 </param>
        /// <param name="localPageUrl">      打开的页面URL，相对路径 </param>
        /// <param name="command">           指令 </param>
        /// <param name="params">            配套的键值对 </param>
        /// <returns> 组装好的actionParam </returns>
        public static string BuildAsOpenBingoTouchLocalAppPage(string bingoTouchAppCode, string localPageUrl, string command, params Param[] @params)
	    {
	        Guard.GuardReqiredString(bingoTouchAppCode, "bingoTouchAppCode must be set value.");
	        Guard.GuardReqiredString(localPageUrl, "pageUrl must be set value.");

	        NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder(command);

	        foreach (Param param in @params)
	        {
	            nativeCommandAndParamsBuilder.Append(param.Key, param.Value);
	        }

	        return nativeCommandAndParamsBuilder.ToString();
	    }

        /// <summary>
        /// 构建一个打开bingotouch远程页面的action参数</summary>
        /// <param name="remotePageUrl"> 远程应用页面，绝对路径 </param>
        /// <returns> 组装好的actionParam </returns>
        public static string BuildAsOpenBingoTouchRemoteAppPage(string remotePageUrl)
		{
			Guard.GuardReqiredString(remotePageUrl, "bingoTouchAppCode must be set value.");

			NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BingoTouch");

			nativeCommandAndParamsBuilder.Append("url", remotePageUrl);

			return nativeCommandAndParamsBuilder.ToString();
		}

		/// <summary>
        /// 构建一个打开特定动态的action参数 </summary>
        /// <param name="blogId"> 动态Id </param>
        /// <returns> 组装好的actionParam </returns>
		public static string BuildAsOpenBlogPage(string blogId)
		{
			Guard.GuardReqiredString(blogId, "blogId must be set value.");

			NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BlogMessage");

			nativeCommandAndParamsBuilder.Append("blogId", blogId);

			return nativeCommandAndParamsBuilder.ToString();
		}

		/// <summary>
        /// 构建一个打开特定原生功能的action参数 </summary>
        /// <param name="command"> 原生功能指令 </param>
        /// <param name="params">  配套的参数 </param>
        /// <returns> 组装好的actionParam </returns>
		public static string BuildAsOpenNativeFunction(string command, params Param[] @params)
		{
			Guard.GuardReqiredString(command, "command must be set value.");

			NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder(command);

			foreach (Param param in @params)
			{
				nativeCommandAndParamsBuilder.Append(param.Key, param.Value);
			}

			return nativeCommandAndParamsBuilder.ToString();
		}
	}

}