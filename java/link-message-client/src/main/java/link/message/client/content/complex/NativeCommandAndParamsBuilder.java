package link.message.client.content.complex;

import link.message.client.utils.Guard;

/**
 * NativeAction所需的actionParam参数构建器
 * actionParam的具体格式是：
 * [command指令]
 * param1=paramValue1
 * param2=paramValue2
 * @author zhong_t
 *
 */
public class NativeCommandAndParamsBuilder {
	protected StringBuilder actionParams = new StringBuilder();
	
	public NativeCommandAndParamsBuilder(String command) {
		Guard.guardReqiredString(command, "command must be set value.");
		
		actionParams.append("[").append(command).append("]");
	}
	
	public NativeCommandAndParamsBuilder append(String key, String value) {
		
		this.actionParams.append("\n").append(key).append("=").append(value);
		
		return this;
	}

	@Override
	public String toString() {
		return this.actionParams.toString();
	}
	
	/**
	 * 构建一个打开bingotouch本地页面的所需action参数
	 * @see #buildAsOpenBingoTouchLocalAppPage(String, String, String, Param...)
	 */
	public static String buildAsOpenBingoTouchLocalAppPage(String bingoTouchAppCode, String localPagePath, Param...params) {
		Param[] paramsNew = null;
		
		int startIndex = 0;
		if (null != params) {
			paramsNew = new Param[params.length+2];
			for(int i=0; i<params.length; ++i) {
				paramsNew[i] = params[i];
			}
			startIndex = params.length;
		} else {
			paramsNew = new Param[2];
		}
		paramsNew[startIndex]   = new Param("appCode", bingoTouchAppCode);
		paramsNew[startIndex+1] = new Param("appUrl", localPagePath);
				
		return buildAsOpenBingoTouchLocalAppPage(bingoTouchAppCode, localPagePath, "OpenApp", paramsNew);
	}
	
	/**
	 * 构建一个打开bingotouch本地页面的所需action参数
	 * @param bingoTouchAppCode 应用编码
	 * @param localPagePath     打开的页面路径，相对路径
	 * @param command           指令
	 * @param params            配套的键值对，在bingotouch页面里可以拿到
	 * @return 组装好的actionParam
	 */
	public static String buildAsOpenBingoTouchLocalAppPage(String bingoTouchAppCode, String localPagePath, String command, Param...params) {
		Guard.guardReqiredString(bingoTouchAppCode, "bingoTouchAppCode must be set value.");
		Guard.guardReqiredString(localPagePath,      "pageUrl must be set value.");
		
		NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder(command);
		
		if (null != params) {
			for (Param param: params) {
				nativeCommandAndParamsBuilder.append(param.getKey(), param.getValue());
			}
		}
		
		return nativeCommandAndParamsBuilder.toString();
	}
	
	/**
	 * 构建一个打开bingotouch远程页面的action参数
	 * @param remotePageUrl 远程应用页面，绝对路径
	 * @return 组装好的actionParam
	 */
	public static String buildAsOpenBingoTouchRemoteAppPage(String remotePageUrl) {
		Guard.guardReqiredString(remotePageUrl, "bingoTouchAppCode must be set value.");
		
		NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BingoTouch");
		
		nativeCommandAndParamsBuilder.append("url", remotePageUrl);
		
		return nativeCommandAndParamsBuilder.toString();
	}
	
	/**
	 * 构建一个打开特定动态的action参数
	 * @param blogId 动态Id
	 * @return 组装好的actionParam
	 */
	public static String buildAsOpenBlogPage(String blogId, Param...params) {
		Guard.guardReqiredString(blogId, "blogId must be set value.");
		
		NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("BlogMessage");
		
		nativeCommandAndParamsBuilder.append("blogId", blogId);
		
		if (null != params) {
			for (Param param: params) {
				nativeCommandAndParamsBuilder.append(param.getKey(), param.getValue());
			}
		}
		
		return nativeCommandAndParamsBuilder.toString();
	}
	
	/**
	 * 构建一个打开特定原生功能的action参数
	 * @param command 原生功能指令
	 * @param params  配套的参数
	 * @return 组装好的actionParam
	 */
	public static String buildAsOpenNativeFunction(String command, Param...params) {
		Guard.guardReqiredString(command, "command must be set value.");
		
		NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder(command);
		
		if (null != params) {
			for (Param param: params) {
				nativeCommandAndParamsBuilder.append(param.getKey(), param.getValue());
			}
		}
		
		return nativeCommandAndParamsBuilder.toString();
	}
	
	/**
	 * 构建自动应答消息的Action参数
	 * @param value 
	 * @param params
	 * @return
	 */
	public static String buildAsRequestAnswer(String value, String params) {
		Guard.guardReqiredString(value, "value must be set value.");
		
		NativeCommandAndParamsBuilder nativeCommandAndParamsBuilder = new NativeCommandAndParamsBuilder("RequestAnswer");
		nativeCommandAndParamsBuilder.append("key", value);
		nativeCommandAndParamsBuilder.append("params", params);
		return nativeCommandAndParamsBuilder.toString();
	}
}
