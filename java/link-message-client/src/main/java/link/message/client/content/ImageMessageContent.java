package link.message.client.content;

/**
 * 图片消息内容
 * 
 * @author zhongt
 *
 */
public class ImageMessageContent extends FileMessageContent {
	
	public ImageMessageContent(String downloadUrl, int size, String fullFileName) {
		super(downloadUrl, size, fullFileName);
	}
	
	public ImageMessageContent(String downloadUrl, int size, String fileName, String extension) {
		super(downloadUrl, size, fileName, extension);
	}
}