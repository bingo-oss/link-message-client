using Link.Message.Client.utils;

namespace Link.Message.Client.content.complex.linear
{
	/// <summary>
	/// Grid类型的线性消息内容项
	/// </summary>
	public class GridLinearComplexMessageContentItem : LinearComplexMessageContentItem
	{
		public GridLinearComplexMessageContentItem()
		{
			DisplayType = LinearComplexMessageContentType.Grid;
		}

		public GridLinearComplexMessageContentItem(GridContent gridContent)
		{
			Guard.GuardReqiredObject(gridContent, "gridContent must be set value.");
			if (gridContent.columns.Count != gridContent.columnCount)
			{
				Guard.ThrowIllegalArgumentException("gridContent's column count not equal columnCount.");
			}

			Content = gridContent;
			DisplayType = LinearComplexMessageContentType.Grid;
		}
	}
}