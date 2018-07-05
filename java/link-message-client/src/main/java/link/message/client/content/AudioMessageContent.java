package link.message.client.content;

/**
 * 音频消息内容
 * @author zhongt
 *
 */
public class AudioMessageContent extends FileMessageContent {
	
	public AudioMessageContent(String downloadUrl, int size, String fileName, String extension) {
		super(downloadUrl, size, fileName, extension);
	}
	
	public AudioMessageContent(String downloadUrl, int size, String fullFileName) {
		super(downloadUrl, size, fullFileName);
	}
}
