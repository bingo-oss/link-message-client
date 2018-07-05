package link.message.client.content;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * 把对象的toString结果作为json转换的结果
 * @author zhongt
 *
 */
public class StringOrObjectSerializer implements ObjectSerializer {
	private static final StringOrObjectSerializer instance = new StringOrObjectSerializer();

	private StringOrObjectSerializer() {
		
	}
	
	public static StringOrObjectSerializer instance() {
		return instance;
	}
	
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
		SerializeWriter out = serializer.getWriter();
		
		if (null == object) {
			out.writeNull();
			return;
		}
		
		if (object instanceof StringContent) {
			out.writeString(object.toString());
			return;
		}
		
		
		out.writeString(object.toString());
	}

}
