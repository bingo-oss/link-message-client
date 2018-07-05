using System;
using System.Collections.Generic;

namespace emb.http.client.utils
{

	/// <summary>
	/// 防御式检查者，负责对输入条件进行检�?
	/// @author zhongt
	/// 
	/// </summary>
	public class Guard
	{

		/// <summary>
		/// 检查必须输入的字符串是否有提供 </summary>
		/// <param name="reqiredString"> 接受检查的字符�? </param>
		/// <param name="exceptionInfo"> 异常提示信息 </param>
		public static void guardReqiredString(string reqiredString, string exceptionInfo)
		{
			if (null == reqiredString || reqiredString.Trim().Length == 0)
			{
				throw new System.ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
		/// 检查必须提供的Object是否为null </summary>
		/// <param name="requiredObject"> 接受检查的对象 </param>
		/// <param name="exceptionInfo">  异常提示信息 </param>
		public static void guardReqiredObject(object requiredObject, string exceptionInfo)
		{
			if (null == requiredObject)
			{
				throw new System.ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
		/// 检查必须提供的集合是否有效 </summary>
		/// <param name="collection">    接受检查的集合 </param>
		/// <param name="exceptionInfo"> 异常提示信息 </param>
		public static void guardReqiredCollection<T1>(ICollection<T1> collection, string exceptionInfo)
		{
			if (null == collection || collection.Count == 0)
			{
				throw new System.ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
		/// 检查必须提供的数组是否有效 </summary>
		/// <param name="array">    接受检查的数组 </param>
		/// <param name="exceptionInfo"> 异常提示信息 </param>
		public static void guardReqiredArray(object[] array, string exceptionInfo)
		{
			if (null == array || array.Length == 0)
			{
				throw new System.ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
		/// 检查int或string值是否在允许的可列举范围之内 </summary>
		/// <param name="value">          接受检查的集合 </param>
		/// <param name="enumValue">      可列举范围的字符�?逗号分隔) </param>
		/// <param name="exceptionInfo">  异常提示信息 </param>
		public static void guardReqiredEnumValue(object value, string enumValue, string exceptionInfo)
		{
			guardReqiredObject(value, "be checked value must be set value.");
			guardReqiredObject(value.ToString(), "be checked value must be set value.");
			guardReqiredString(enumValue, "enum value must be set value.");

			string[] enumValues = enumValue.Split(",", true);

			if (value is string || value is int?)
			{
				foreach (string enumValueItem in enumValues)
				{
					if (value.ToString().Equals(enumValueItem.Trim(), StringComparison.CurrentCultureIgnoreCase))
					{
						return;
					}
				}
				throw new System.ArgumentException(exceptionInfo);
			}

			throw new System.ArgumentException("value's type must be string or number");
		}

		/// <summary>
		/// 抛出输入条件不符合要求的异常信息 </summary>
		/// <param name="exceptionInfo"> 异常信息 </param>
		public static void throwIllegalArgumentException(string exceptionInfo)
		{
			throw new System.ArgumentException(exceptionInfo);
		}
	}

}