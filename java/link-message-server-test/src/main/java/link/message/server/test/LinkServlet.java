/**
 * This file created at 2015年2月3日.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package link.message.server.test;

import link.message.client.content.MessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.content.complex.Action;
import link.message.client.content.complex.ComplexMessageContent;
import link.message.client.content.complex.ComplexMessageContentItem;
import link.message.client.content.complex.ComplexMessageType;
import link.message.client.content.complex.NativeCommandAndParamsBuilder;
import link.message.client.event.EventHandlerServlet;

import com.alibaba.fastjson.JSONObject;


/**
 * <code>{@link LinkServlet}</code>
 *
 * @author zhongt
 */
@SuppressWarnings("serial")
public class LinkServlet extends EventHandlerServlet {

	/* (non-Javadoc)
	 * @see link.message.client.event.EventHandlerServlet#getReplyContent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.alibaba.fastjson.JSONObject)
	 */
	@Override
	protected MessageContent getReplyContent(String loginId, String loginName,
			String key, String value, String params, JSONObject userInputParams) {
		if (value.trim().equals("1") || value.trim().equals("2")) {
			if (null != params) {
				TextMessageContent content = new TextMessageContent(params);
				return content;
			}
		}
		
		ComplexMessageContent content = new ComplexMessageContent("应答消息", ComplexMessageType.AUTO_REPLY);
		ComplexMessageContentItem item1 = new ComplexMessageContentItem("java", Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsRequestAnswer("1", "{java技术}")));
		ComplexMessageContentItem item2 = new ComplexMessageContentItem(".net", Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsRequestAnswer("2", "{.net技术}")));
		content.addMessageContentItem(item1);
		content.addMessageContentItem(item2);
		return content;
	}

}
