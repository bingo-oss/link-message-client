using System.Runtime.Serialization;

namespace Link.Message.Client.content.complex.linear
{

	/// <summary>
	/// 对齐方式
	/// </summary>
	public enum Alignment
	{
        [EnumMember(Value = "left")]
		Left,
        [EnumMember(Value = "center")]
		Center,
        [EnumMember(Value = "right")]
		Right
	}
}