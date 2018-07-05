package link.message.client.content.complex.linear;

import link.message.client.utils.Guard;


/**
 * Grid类型的线性消息内容项
 * 
 * @author zhongt
 *
 */
public class GridLinearComplexMessageContentItem extends LinearComplexMessageContentItem {
	public GridLinearComplexMessageContentItem() {
		this.displayType = LinearComplexMessageContentType.GRID;
	}
	
	public GridLinearComplexMessageContentItem(GridContent gridContent) {
		Guard.guardReqiredObject(gridContent, "gridContent must be set value.");
		if (gridContent.columns.size() != gridContent.columnCount) {
			Guard.throwIllegalArgumentException("gridContent's column count not equal columnCount.");
		}
		
		this.content     = gridContent;
		this.displayType = LinearComplexMessageContentType.GRID;
	}
}