package link.message.client.content;

import link.message.client.utils.Guard;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 文件消息内容
 * @author zhongt
 *
 */
public class FileMessageContent extends MessageContent {
	@JSONField(name="file_name")
	protected String fileName;
	protected int    size;
	@JSONField(name="download_url")
	protected String downloadUrl;
	protected String extension;

	public FileMessageContent() {
		
	}
	
	public FileMessageContent(String downloadUrl, int size, String fileName, String extension) {
		this(downloadUrl, size, fileName.concat(".").concat(extension));
	}
	
	public FileMessageContent(String downloadUrl, int size, String fullFileName) {
		Guard.guardReqiredString(downloadUrl,  "downloadUrl must be set value.");
		Guard.guardReqiredString(fullFileName, "fullFileName must be set value.");
		Guard.guardReqiredObject(size,         "size must be set value.");
		
		this.downloadUrl = downloadUrl;
		this.size        = size;
		
		int position = fullFileName.lastIndexOf(".");
		extension = position <=0 ? null : fullFileName.trim().substring(position);
		fileName  = fullFileName.trim().substring(0, position); 
		
		Guard.guardReqiredObject(size, "fullFileName must include extension name.");
		Guard.guardReqiredObject(size, "fullFileName must include file name.");
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
