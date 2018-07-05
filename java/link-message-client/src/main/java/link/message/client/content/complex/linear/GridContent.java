package link.message.client.content.complex.linear;

import java.util.ArrayList;
import java.util.List;

import link.message.client.StringOrObjectSerializable;
import link.message.client.utils.Guard;

import com.alibaba.fastjson.annotation.JSONField;

public class GridContent implements StringOrObjectSerializable {
	@JSONField(name="column")
	protected List<Column> columns = new ArrayList<Column>();
	@JSONField(name="data")
	protected List<String[]> datas  = new ArrayList<String[]>();
	@JSONField(serialize=false)
	protected int columnCount;
	
	public GridContent(int columnCount) {
		this.columnCount = columnCount;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<String[]> getDatas() {
		return datas;
	}
	
	public int getColumnCount() {
		return columnCount;
	}

	public void addColumn(Column column) {
		Guard.guardReqiredObject(column, "column must be set value.");
		
		if (columns.size() == columnCount) {
			Guard.throwIllegalArgumentException("grid columns size already equal column count.");
		}
		
		columns.add(column);
	}
	
	public void addData(String[] data) {
		Guard.guardReqiredArray(data, "data must be set value.");
		
		if (data.length != columnCount) {
			Guard.throwIllegalArgumentException("data array's size must equal column count.");
		}
		
		datas.add(data);
	}
}