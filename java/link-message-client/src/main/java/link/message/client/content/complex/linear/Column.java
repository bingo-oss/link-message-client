package link.message.client.content.complex.linear;

/**
 * Grid类型的线性消息里，对猎头的定义
 * @author zhongt
 *
 */
public class Column {
	protected String    title;
	protected Alignment align;
	protected int       width;
	
	public Column() {
		
	}
	
	public Column(String title, Alignment align, int width) {
		this.title = title;
		this.align = align;
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Alignment getAlign() {
		return align;
	}

	public void setAlign(Alignment align) {
		this.align = align;
	}
}