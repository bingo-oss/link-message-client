package link.message.client.utils;

import java.util.Collection;

/**
 * 防御式检查者，负责对输入条件进行检查
 * @author zhongt
 *
 */
public class Guard {
	
	/**
	 * 检查必须输入的字符串是否有提供
	 * @param reqiredString 接受检查的字符串
	 * @param exceptionInfo 异常提示信息
	 */
	public static void guardReqiredString(String reqiredString, String exceptionInfo) {
		if (null == reqiredString || reqiredString.trim().length() == 0) {
			throw new IllegalArgumentException(exceptionInfo);
		}
	}
	
	/**
	 * 检查必须提供的Object是否为null
	 * @param requiredObject 接受检查的对象
	 * @param exceptionInfo  异常提示信息
	 */
	public static void guardReqiredObject(Object requiredObject, String exceptionInfo) {
		if (null == requiredObject) {
			throw new IllegalArgumentException(exceptionInfo);
		}
	}
	
	/**
	 * 检查必须提供的集合是否有效
	 * @param collection    接受检查的集合
	 * @param exceptionInfo 异常提示信息
	 */
	public static void guardReqiredCollection(Collection<?> collection, String exceptionInfo) {
		if (null == collection || collection.isEmpty()) {
			throw new IllegalArgumentException(exceptionInfo);
		}
	}
	
	/**
	 * 检查必须提供的数组是否有效
	 * @param array    接受检查的数组
	 * @param exceptionInfo 异常提示信息
	 */
	public static void guardReqiredArray(Object[] array, String exceptionInfo) {
		if (null == array || array.length == 0) {
			throw new IllegalArgumentException(exceptionInfo);
		}
	}
	
	/**
	 * 检查int或string值是否在允许的可列举范围之内
	 * @param value          接受检查的集合
	 * @param enumValue      可列举范围的字符串(逗号分隔)
	 * @param exceptionInfo  异常提示信息
	 */
	public static void guardReqiredEnumValue(Object value, String enumValue, String exceptionInfo) {
		guardReqiredObject(value, "be checked value must be set value.");
		guardReqiredString(enumValue, "enum value must be set value.");
		
		String[] enumValues = enumValue.split(",");
		
		if (value instanceof String || value instanceof Integer) {
			for (String enumValueItem: enumValues) {
				if (value.toString().equalsIgnoreCase(enumValueItem.trim())) {
					return;
				}
			}
			throw new IllegalArgumentException(exceptionInfo);
		}
		
		throw new IllegalArgumentException("value's type must be string or number");
	}
	
	/**
	 * 抛出输入条件不符合要求的异常信息
	 * @param exceptionInfo 异常信息
	 */
	public static void throwIllegalArgumentException(String exceptionInfo) {
		throw new IllegalArgumentException(exceptionInfo);
	}
}
