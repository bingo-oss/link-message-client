using System.Collections.Generic;
using System.Text;
using Link.Message.Client.utils;

namespace Link.Message.Client.messager
{
	/// <summary>
    /// ��������Ϣ������
	/// </summary>
	public class MultiMessageReceiver : MessageReceiver
	{
        public PersonMessageReceiverIdType IdType { set; get; }
	    public int Length { set; get; }

		/// <summary>
        /// ������Ϣ������Ա���� </summary>
        /// <param name="personMessageReceivers">��Ϣ�����߼���</param>
        /// <param name="personMessageReceiverIdType">��Ϣ�����ߵ�ID����  1ΪloginId 2ΪuserId</param>
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
                // ��˾�ǿ�ѡ��
				if (null != receiver.ToCompany && receiver.ToCompany.Trim().Length > 0)
				{
					toCompanies.Append(",").Append(receiver.ToCompany);
				}

                // ���ý����ߵĸ���
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