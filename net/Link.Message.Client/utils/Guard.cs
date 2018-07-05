using System;
using System.Collections.Generic;

namespace Link.Message.Client.utils
{

	/// <summary>
    /// 防御式检查者，负责对输入条件进行检查
	/// </summary>
	public class Guard
	{

		/// <summary>
        /// 检查必须输入的字符串是否有提供</summary>
        /// <param name="reqiredString">接受检查的字符串</param>
        /// <param name="exceptionInfo">异常提示信息</param>
		public static void GuardReqiredString(string reqiredString, string exceptionInfo)
		{
			if (null == reqiredString || reqiredString.Trim().Length == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// 检查必须提供的Object是否为null</summary>
        /// <param name="requiredObject">接受检查的对象</param>
        /// <param name="exceptionInfo">异常提示信息</param>
		public static void GuardReqiredObject(object requiredObject, string exceptionInfo)
		{
			if (null == requiredObject)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// 检查必须提供的集合是否有效</summary>
        /// <param name="collection">接受检查的集合</param>
        /// <param name="exceptionInfo">异常提示信息</param>
		public static void GuardReqiredCollection<T1>(ICollection<T1> collection, string exceptionInfo)
		{
			if (null == collection || collection.Count == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// 检查必须提供的数组是否有效</summary>
        /// <param name="array">接受检查的数组</param>
        /// <param name="exceptionInfo">异常提示信息</param>
		public static void GuardReqiredArray(string[] array, string exceptionInfo)
		{
			if (null == array || array.Length == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// 抛出输入条件不符合要求的异常信息 </summary>
        /// <param name="exceptionInfo">异常信息</param>
		public static void ThrowIllegalArgumentException(string exceptionInfo)
		{
			throw new ArgumentException(exceptionInfo);
		}
	}

}