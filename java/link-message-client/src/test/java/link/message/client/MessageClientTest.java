package link.message.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import link.message.client.content.MessageContent;
import link.message.client.content.NewRichMessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.content.complex.Action;
import link.message.client.content.complex.ComplexMessageContent;
import link.message.client.content.complex.ComplexMessageContentItem;
import link.message.client.content.complex.ComplexMessageType;
import link.message.client.content.complex.NativeCommandAndParamsBuilder;
import link.message.client.content.complex.linear.Alignment;
import link.message.client.content.complex.linear.Column;
import link.message.client.content.complex.linear.FormContent;
import link.message.client.content.complex.linear.FormFieldConfig;
import link.message.client.content.complex.linear.FormLinearComplexMessageContentItem;
import link.message.client.content.complex.linear.GridContent;
import link.message.client.content.complex.linear.GridLinearComplexMessageContentItem;
import link.message.client.content.complex.linear.TextLinearComplexMessageContentItem;
import link.message.client.content.complex.linear.TitleLinearComplexMessageContentItem;
import link.message.client.messager.MultiMessageReceiver;
import link.message.client.messager.PersonMessageReceiver;
import link.message.client.messager.PersonMessageReceiverIdType;

public class MessageClientTest {
	
	protected MessageClient messageClient;
	
	@Before
	public void setUp() throws Exception {
		/*messageClient = new MessageClient("http://linktest.bingocc.cc:10082/svrnum/",
				                          "43b80351-2c0a-4e84-a3d0-43eb7d35bc00",
				                          "43b80351-2c0a-4e84-a3d0-43eb7d35bc00");*/
		messageClient = new MessageClient("http://10.201.76.141:10082/svrnum/",
                "e9468a1a-37f9-11e4-b316-dc0ea18fc32-",
                "43b80351-2c0a-4e84-a3d0-43eb7d35bc00");
    	/*messageClient = new MessageClient("http://10.201.76.93:10082/svrnum",
                "3ecc8782-d1bd-45dc-88a5-b65d83dc5c30",
                "1e5bc77ad12f496b8f9d038291de3680");*/
	}

	@After
	public void tearDown() throws Exception {
		messageClient = null;
	}

	@Test
	public void testSendTextMessage() {
		MessageContent msg = new TextMessageContent("简单文本消息");
		msg.setToDeviceTypes("02");
		SendMessageResult result = messageClient.sendSingleMessage(msg, new PersonMessageReceiver("admin", "管理员"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendImageMessage() {
		
	}
	
	@Test
	public void testSendComplexTextMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("复杂文本消息概要", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这里是复杂文本消息的内容");
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendComplexTextMessageAsFactoryMethod() {
		SendMessageResult result = messageClient.sendSingleMessage(ComplexMessageContent.instanceAsTextMessageContent("复杂文本消息概要", "这里是复杂文本消息的内容"), 
				                                                   new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendComplexTextMessageWithActionAsFactoryMethod() {
		SendMessageResult result = messageClient.sendSingleMessage(ComplexMessageContent.instanceAsTextMessageContent("简单文本消息概要", "这里是简单问题消息的内容",
				                                                   Action.instanceAsOpenUrl("http://www.mi.com")), 
				                                                   new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}

	@Test
	public void testSendImageTextMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("图文消息消息概要", ComplexMessageType.IMAGE_TEXT);
		ComplexMessageContentItem contentItem1 = new ComplexMessageContentItem("图文消息--URL", 
				                                                               "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
				                                                               Action.instanceAsOpenUrl("http://www.mi.com")
				                                                              );
		complexTextMessage.addMessageContentItem(contentItem1);
		ComplexMessageContentItem contentItem2 = new ComplexMessageContentItem("图文消息--HTML", 
				                                                               "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png", 
				                                                               Action.instanceAsOpenHtml("<b>这里是富文本消息的基本内容</b>")
				                                                              );
		complexTextMessage.addMessageContentItem(contentItem2);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendNewRichMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("新富文本消息消息概要", ComplexMessageType.NEW_RICH_TEXT);
		NewRichMessageContent newRichMessageContent = new NewRichMessageContent("新富文本消息", "这里是新富文本消息的基本内容");
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem(newRichMessageContent, 
																              "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
																              Action.instanceAsOpenHtml("<b>你好，这里是新富文本消息的详细内容！！</b>")  
																             );
		complexTextMessage.addMessageContentItem(contentItem);
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendRichMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("富文本消息消息概要", ComplexMessageType.RICH_TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这是富文本消息", 
																              "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
																              Action.instanceAsOpenHtml("<b>你好，富文本消息！！</b>")
																             );
		complexTextMessage.addMessageContentItem(contentItem);
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendGridLinearMessage() {
		ComplexMessageContent complexMessageContent = new ComplexMessageContent("Grid线性消息概要", ComplexMessageType.LINEAR);
		
		TitleLinearComplexMessageContentItem title = new TitleLinearComplexMessageContentItem("Grid标题");
		TextLinearComplexMessageContentItem  hint  = new TextLinearComplexMessageContentItem("Grid提示");
		
		GridContent gridContent = new GridContent(2);
		gridContent.addColumn(new Column("姓名", Alignment.center, 40));
		gridContent.addColumn(new Column("部门", Alignment.left, 60));
		gridContent.addData(new String[] {"张三","云应用平台部"});
		gridContent.addData(new String[] {"李四","云应用体验部"});
		GridLinearComplexMessageContentItem grid = new GridLinearComplexMessageContentItem(gridContent);
		
		complexMessageContent.addMessageContentItem(title).addMessageContentItem(grid).addMessageContentItem(hint);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendFormLinearMessage() {
		ComplexMessageContent complexMessageContent = new ComplexMessageContent("Form线性消息概要", ComplexMessageType.LINEAR);
		
		TitleLinearComplexMessageContentItem title = new TitleLinearComplexMessageContentItem("Form标题");
		TextLinearComplexMessageContentItem  hint  = new TextLinearComplexMessageContentItem("Hint提示");
		
		FormContent formContent = new FormContent(new FormFieldConfig(30,Alignment.center), new FormFieldConfig(70, Alignment.left));
		formContent.addData("姓名", "张三");
		formContent.addData("籍贯", "广州");
		formContent.addData("年龄", "30");
		formContent.addData("爱好", "唱歌、跳舞");
		FormLinearComplexMessageContentItem form = new FormLinearComplexMessageContentItem(formContent);
		
	    complexMessageContent.addMessageContentItem(title).addMessageContentItem(form).addMessageContentItem(hint);
		
	    SendMessageResult result = messageClient.sendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendOpenNativeFunctionMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个原生指令的Action消息", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开原生的聊天页面", 
				Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsOpenNativeFunction("StartChat")));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendOpenNativeFunctionMessageAsOpenBingoTouchLocalPage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个Bingotouch本地应用页面的Action消息", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开BingoTouch本地应用页面", 
				Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsOpenBingoTouchLocalAppPage("BingoService", "/modules/leave/addleave.html")));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendOpenNativeFunctionMessageAsOpenBingoTouchRemotePage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个Bingotouch远程页面的Action消息", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开BingoTouch远程应用页面", 
				Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsOpenBingoTouchRemoteAppPage("http://www.mi.com")));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendOpenURLMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个远程URL的Action消息", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开远程URL页面", 
				Action.instanceAsOpenUrl("http://www.xiaomi.com"));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendOpenHTMLMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个HTML的Action消息", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开HTML页面", 
				Action.instanceAsOpenHtml("<div><b>这里是HTML的详细内容</b></div>"));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}
	
	@Test
	public void testSendMultiTextMessage() {
		List<PersonMessageReceiver> personMessageReceivers = new ArrayList<PersonMessageReceiver>();
		personMessageReceivers.add(new PersonMessageReceiver("zhongt", "钟涛"));
		personMessageReceivers.add(new PersonMessageReceiver("wuyang", "武扬"));
		MultiMessageReceiver multiMessageReceiver = new MultiMessageReceiver(personMessageReceivers, PersonMessageReceiverIdType.LOGIN_ID);
		SendMessageResult result = messageClient.sendMultiMessage(new TextMessageContent("简单文本消息"), multiMessageReceiver);
		Assert.assertEquals(true, result.isSuccess());
	}
	
	
	
	/*
	@Test
	public void testSendOpenLocalBingoTouchApp() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("复杂文本消息概要", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这里是复杂文本消息的内容", 
				                                Action.instanceAsOpenNative(NativeCommandAndParamsBuilder.buildAsOpenBingoTouchLocalAppPage(
				                                		"BingoService", "/modules/leave/addleave.html")));
		complexTextMessage.addMessageContentItem(contentItem);
		
		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}*/
}
