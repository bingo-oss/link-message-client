using System.Collections.Generic;
using Newtonsoft.Json;

namespace Link.Message.Client.content.complex.linear
{
	public class FormContent : IStringOrObjectSerializable
	{
	    public FormFieldConfig Key { get; set; }
	    public FormFieldConfig Value { get; set; }
	    protected internal IList<FormFieldValue> datas = new List<FormFieldValue>();

		public FormContent(FormFieldConfig key, FormFieldConfig value)
		{
			Key = key;
			Value = value;
		}

        [JsonProperty("data")]
		public virtual IList<FormFieldValue> Datas
		{
			get
			{
				return datas;
			}
		}

		public virtual void AddData(FormFieldValue data)
		{
			datas.Add(data);
		}

		public virtual void AddData(string key, string value)
		{
			datas.Add(new FormFieldValue(key, value));
		}
	}

}