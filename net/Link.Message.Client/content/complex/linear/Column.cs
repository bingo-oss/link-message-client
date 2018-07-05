using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Link.Message.Client.content.complex.linear
{

	/// <summary>
    /// Grid���͵�������Ϣ�����ͷ�Ķ���
	/// </summary>
	public class Column
	{
		public string Title { set; get; }
        [JsonConverter(typeof(StringEnumConverter))]
	    public Alignment Align { get; set; }
	    public int Width { set; get; }

	    public Column()
		{

		}

		public Column(string title, Alignment align, int width)
		{
			this.Title = title;
			this.Align = align;
			this.Width = width;
		}
	}
}