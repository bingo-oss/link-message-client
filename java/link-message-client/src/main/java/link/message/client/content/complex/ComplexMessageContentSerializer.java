package link.message.client.content.complex;

import java.io.IOException;
import java.lang.reflect.Type;

import link.message.client.content.StringContent;
import link.message.client.content.StringOrObjectSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * ComplexMessageContent序列化为JSON时做转换
 * @author zhongt
 *
 */
public class ComplexMessageContentSerializer implements ObjectSerializer {
	private static final ComplexMessageContentSerializer instance = new ComplexMessageContentSerializer();
	
	private ComplexMessageContentSerializer() {
		
	}
	
	public static ComplexMessageContentSerializer instance() {
		return instance;
	}

	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
		SerializeWriter out = serializer.getWriter();
		
		if (null == object) {
			out.writeNull();
		}
		
		SerializeConfig config = new SerializeConfig();
		config.put(StringContent.class, StringOrObjectSerializer.instance());
		out.writeString(JSON.toJSONString(object,config).toString());
	}

}
