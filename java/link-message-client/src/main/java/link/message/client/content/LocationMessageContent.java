package link.message.client.content;

/**
 * 地理位置信息内容
 * 
 * @author zhongt
 *
 */
public class LocationMessageContent extends MessageContent {
	private String addr;
	private String longitude;
	private String latitude;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
