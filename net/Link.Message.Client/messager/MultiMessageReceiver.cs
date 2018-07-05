using System.Collections.Generic;
using System.Text;
using Link.Message.Client.utils;

namespace Link.Message.Client.messager
{
	/// <summary>
    /// 批量的消息接受者
	/// </summary>
	public class MultiMessageReceiver : MessageReceiver
	{
        public PersonMessageReceiverIdType IdType { set; get; }
	    public int Length { set; get; }

		/// <summary>
        /// 批量消息接收人员构造 </summary>
        /// <param name="personMessageReceivers">消息接受者集合</param>
        /// <param name="personMessageReceiverIdType">消息接受者的ID类型  1为loginId 2为userId</param>
        public MultiMessageReceiver(IList<PersonMessageReceiver> personMessageReceivers, PersonMessageReceiverIdType personMessageReceiverIdType)
		{
			Guard.GuardReqiredCollection(personMessageReceivers, "message receivers object must be set value.");

			IdType = personMessageReceiverIdType;

			StringBuilder toIds = new StringBuilder();
			StringBuilder toNames = new StringBuilder();
			StringBuilder toTypes = new StringBuilder();
			StringBuilder toCompanies = new StringBuilder();

			foreach (MessageReceiver receiver in personMessageReceivers)
			{
				toIds.Append(",").Append(receiver.ToId);
				toNames.Append(",").Append(receiver.ToName);
				toTypes.Append(",").Append(receiver.ToType);
                // 公司是可选的
				if (null != receiver.ToCompany && receiver.ToCompany.Trim().Length > 0)
				{
					toCompanies.Append(",").Append(receiver.ToCompany);
				}

                // 设置接受者的个数
				Length++;
			}

			ToId   = toIds.ToString().Substring(1);
			ToName = toNames.ToString().Substring(1);
			//TODO ToType = toTypes.ToString().Substring(1);

			if (toCompanies.Length > 0)
			{
				ToCompany = toCompanies.ToString().Substring(1);
			}
		}
	}
}