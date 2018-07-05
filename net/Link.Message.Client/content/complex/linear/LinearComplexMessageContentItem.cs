using Newtonsoft.Json;

namespace Link.Message.Client.content.complex.linear
{
	/// <summary>
	/// 负责消息里的线性消息项
	/// </summary>
	public class LinearComplexMessageContentItem : ComplexMessageContentItem
	{
	    [JsonProperty("display_type")]
	    public int DisplayType { get; set; }
	}
}