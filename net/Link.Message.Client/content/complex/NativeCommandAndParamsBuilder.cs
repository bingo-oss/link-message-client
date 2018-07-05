using System.Text;
using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex
{
	/// <summary>
    /// NativeAction�����actionParam����������
	/// actionParam�ľ����ʽ�ǣ�
    ///[commandָ��]
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
        /// ����һ����bingotouch����ҳ�������action���� </summary>
        /// <param name="bingoTouchAppCode"> Ӧ�ñ��� </param>
        /// <param name="localPageUrl">      �򿪵�ҳ��URL�����·�� </param>
        /// <param name="params">            ���׵ļ�ֵ�ԯ� </param>
        /// <returns> ��װ�õ�actionParam </returns>
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
        /// ����һ����bingotouch����ҳ�������action���� </summary>
        /// <param name="bingoTouchAppCode"> Ӧ�ñ��� </param>
        /// <param name="localPageUrl">      �򿪵�ҳ��URL�����·�� </param>
        /// <param name="command">           ָ� </param>
        /// <param name="params">            ���׵ļ�ֵ�ԯ� </param>
        /// <returns> ��װ�õ�actionParam </returns>
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
        /// ����һ����bingotouchԶ��ҳ���action����</summary>
        /// <param name="remotePageUrl"> Զ��Ӧ��ҳ�棬����·�� </param>
        /// <returns> ��װ�õ�actionParam </returns>
        public static string BuildAsOpenBingoTouchRemoteAppPage(string remotePageUrl)
		{
			Guard.GuardReqiredString(remotePageUrl, "bingoTouchAppCode must be set value.");

			NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BingoTouch");

			nativeCommandAndParamsBuilder.Append("url", remotePageUrl);

			return nativeCommandAndParamsBuilder.ToString();
		}

		/// <summary>
        /// ����һ�����ض���̬��action���� </summary>
        /// <param name="blogId"> ��̬Id </param>
        /// <returns> ��װ�õ�actionParam </returns>
		public static string BuildAsOpenBlogPage(string blogId)
		{
			Guard.GuardReqiredString(blogId, "blogId must be set value.");

			NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BlogMessage");

			nativeCommandAndParamsBuilder.Append("blogId", blogId);

			return nativeCommandAndParamsBuilder.ToString();
		}

		/// <summary>
        /// ����һ�����ض�ԭ�����ܵ�action���� </summary>
        /// <param name="command"> ԭ������ָ�� </param>
        /// <param name="params">  ���׵Ĳ��� </param>
        /// <returns> ��װ�õ�actionParam </returns>
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