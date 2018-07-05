package link.message.client.content;

/**
 * 视频消息内容
 * @author zhongt
 *
 */
public class VideoMessageContent extends FileMessageContent {
	
	public VideoMessageContent(String downloadUrl, int size, String fileName, String extension) {
		super(downloadUrl, size, fileName, extension);
	}
	
	public VideoMessageContent(String downloadUrl, int size, String fullFileName) {
		super(downloadUrl, size, fullFileName);
	}
}
