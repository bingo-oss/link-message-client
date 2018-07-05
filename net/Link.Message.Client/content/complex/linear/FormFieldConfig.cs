using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Link.Message.Client.content.complex.linear
{

	using Guard = utils.Guard;

	/// <summary>
    /// Form类型线性消息内容项的表单配置
	/// </summary>
	public class FormFieldConfig
	{
		private int _width = 75;
		private Alignment _align = Alignment.Left;

		public FormFieldConfig()
		{

		}

		public FormFieldConfig(int width, Alignment align)
		{
			Guard.GuardReqiredObject(width, "width must be set value.");
			_width = width;
			_align = align;
		}

		public virtual int Width
		{
			get
			{
				return _width;
			}
			set
			{
				_width = value;
			}
		}

        [JsonConverter(typeof(StringEnumConverter))]
		public virtual Alignment Align
		{
            get
            {
                return _align;
            }
            set
            {
                _align = value;
            }
		}
	}

}