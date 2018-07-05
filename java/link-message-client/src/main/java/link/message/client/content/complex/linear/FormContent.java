package link.message.client.content.complex.linear;

import java.util.ArrayList;
import java.util.List;

import link.message.client.StringOrObjectSerializable;

import com.alibaba.fastjson.annotation.JSONField;

public class FormContent implements StringOrObjectSerializable {
	protected FormFieldConfig       key;
	protected FormFieldConfig       value;
	@JSONField(name="data")
	protected List<FormFieldValue>  datas = new ArrayList<FormFieldValue>();
	
	public FormContent(FormFieldConfig key, FormFieldConfig value) {
		this.key   = key;
		this.value = value;
	}

	public FormFieldConfig getKey() {
		return key;
	}

	public void setKey(FormFieldConfig key) {
		this.key = key;
	}

	public FormFieldConfig getValue() {
		return value;
	}

	public void setValue(FormFieldConfig value) {
		this.value = value;
	}

	public List<FormFieldValue> getDatas() {
		return datas;
	}
	
	public void addData(FormFieldValue data) {
		this.datas.add(data);
	}
	
	public void addData(String key, String value) {
		this.datas.add(new FormFieldValue(key, value));
	}
}
