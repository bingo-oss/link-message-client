package link.message.client.content.complex;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

import link.message.client.content.StringContent;
import link.message.client.content.StringOrObjectSerializer;
import link.message.client.content.TextMessageContent;

public class ComplexMessageContentItemTest {

	private ComplexMessageContentItem complexMessageContentItem = new ComplexMessageContentItem();
	
	@Test
	public void testToJson() {
		String testResult = JSON.toJSON(complexMessageContentItem).toString();
		System.out.println(testResult);

		SerializeConfig config = new SerializeConfig();
		config.put(StringContent.class,         StringOrObjectSerializer.instance());
		config.put(TextMessageContent.class,    StringOrObjectSerializer.instance());
		config.put(ComplexMessageContent.class, ComplexMessageContentSerializer.instance());
		testResult = JSON.toJSON(complexMessageContentItem, config).toString();
		System.out.println(testResult);
	}
}