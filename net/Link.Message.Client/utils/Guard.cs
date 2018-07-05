using System;
using System.Collections.Generic;

namespace Link.Message.Client.utils
{

	/// <summary>
    /// ����ʽ����ߣ�����������������м��
	/// </summary>
	public class Guard
	{

		/// <summary>
        /// ������������ַ����Ƿ����ṩ</summary>
        /// <param name="reqiredString">���ܼ����ַ���</param>
        /// <param name="exceptionInfo">�쳣��ʾ��Ϣ</param>
		public static void GuardReqiredString(string reqiredString, string exceptionInfo)
		{
			if (null == reqiredString || reqiredString.Trim().Length == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// �������ṩ��Object�Ƿ�Ϊnull</summary>
        /// <param name="requiredObject">���ܼ��Ķ���</param>
        /// <param name="exceptionInfo">�쳣��ʾ��Ϣ</param>
		public static void GuardReqiredObject(object requiredObject, string exceptionInfo)
		{
			if (null == requiredObject)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// �������ṩ�ļ����Ƿ���Ч</summary>
        /// <param name="collection">���ܼ��ļ���</param>
        /// <param name="exceptionInfo">�쳣��ʾ��Ϣ</param>
		public static void GuardReqiredCollection<T1>(ICollection<T1> collection, string exceptionInfo)
		{
			if (null == collection || collection.Count == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// �������ṩ�������Ƿ���Ч</summary>
        /// <param name="array">���ܼ�������</param>
        /// <param name="exceptionInfo">�쳣��ʾ��Ϣ</param>
		public static void GuardReqiredArray(string[] array, string exceptionInfo)
		{
			if (null == array || array.Length == 0)
			{
				throw new ArgumentException(exceptionInfo);
			}
		}

		/// <summary>
        /// �׳���������������Ҫ����쳣��Ϣ </summary>
        /// <param name="exceptionInfo">�쳣��Ϣ</param>
		public static void ThrowIllegalArgumentException(string exceptionInfo)
		{
			throw new ArgumentException(exceptionInfo);
		}
	}

}