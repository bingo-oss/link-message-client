package link.message.client.content;

/**
 * 语音消息内容
 * @author zhongt
 *
 */
public class VoiceMessageContent extends FileMessageContent {
	
	public VoiceMessageContent(String downloadUrl, int size, String fileName, String extension) {
		super(downloadUrl, size, fileName, extension);
	}
	
	public VoiceMessageContent(String downloadUrl, int size, String fullFileName) {
		super(downloadUrl, size, fullFileName);
	}
}
