using System;
using System.Collections.Generic;
using Link.Message.Client.content;
using Link.Message.Client.content.complex;
using Link.Message.Client.content.complex.linear;
using Link.Message.Client.messager;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Action = Link.Message.Client.content.complex.Action;

namespace Link.Message.Client.Test
{
    /// <summary>
    /// 测试消息客户端
    /// </summary>
    [TestClass]
    public class MessageClientTest
    {
        private readonly MessageClient _messageClient;

        public MessageClientTest()
        {
            /*_messageClient = new EmbHttpClient("http://co3.gz-mstc.com:10082/svrnum/",
				                          "33ac5630-3ab4-4975-a862-8002c24e3a45",
				                          "3a6e700ed51d4cbf8ea70d98b2d97365");*/

            _messageClient = new MessageClient("http://172.167.2.217:89/svrnum/",
                    "3d236efb-389d-4221-8f61-b77d0af79d40",
                    "3d236efb-389d-4221-8f61-b77d0af79d40");
        }

        [TestMethod]
        public void TestSendTextMessage()
        {
            SendMessageResult result = _messageClient.SendSingleMessage(new TextMessageContent("简单文本消息"), new PersonMessageReceiver("zhongt", "钟涛"));
            Assert.AreEqual(true, result.Success);
        }

        [TestMethod]
        public void TestSendComplexTextMessage()
        {
            ComplexMessageContent complexMessageContent = new ComplexMessageContent("复杂文本消息概要", ComplexMessageType.Text);
            ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这里是复杂文本消息的内容");
            complexMessageContent.AddMessageContentItem(contentItem);

            SendMessageResult result = _messageClient.SendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
            Assert.AreEqual(true, result.Success);
        }

        [TestMethod]
        public void TestSendComplexTextMessageAsFactoryMethod() 
        {
		    SendMessageResult result = _messageClient.SendSingleMessage(ComplexMessageContent.InstanceAsTextMessageContent("复杂文本消息概要", "这里是复杂文本消息的内容"), 
				                                                       new PersonMessageReceiver("zhongt", "钟涛"));
            Assert.AreEqual(true, result.Success);
	    }

        [TestMethod]
	public void TestSendComplexTextMessageWithActionAsFactoryMethod() {
		SendMessageResult result =  _messageClient.SendSingleMessage(ComplexMessageContent.InstanceAsTextMessageContent("简单文本消息概要", "这里是简单问题消息的内容",
				                                                   Action.InstanceAsOpenUrl("http://www.mi.com")), 
				                                                   new PersonMessageReceiver("zhongt", "钟涛"));
        Assert.AreEqual(true, result.Success);
	}

	[TestMethod]
	public void TestSendImageTextMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("图文消息消息概要", ComplexMessageType.ImageText);
		ComplexMessageContentItem contentItem1 = new ComplexMessageContentItem("图文消息--URL", 
				                                                               "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
				                                                               Action.InstanceAsOpenUrl("http://www.mi.com")
				                                                              );
		complexTextMessage.AddMessageContentItem(contentItem1);
		ComplexMessageContentItem contentItem2 = new ComplexMessageContentItem("图文消息--HTML", 
				                                                               "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png", 
				                                                               Action.InstanceAsOpenHtml("<b>这里是富文本消息的基本内容</b>")
				                                                              );
		complexTextMessage.AddMessageContentItem(contentItem2);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
        Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendNewRichMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("新富文本消息消息概要", ComplexMessageType.NewRichText);
		NewRichMessageContent newRichMessageContent = new NewRichMessageContent("新富文本消息", "这里是新富文本消息的基本内容");
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem(newRichMessageContent, 
																              "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
																              Action.InstanceAsOpenHtml("<b>你好，这里是新富文本消息的详细内容！！</b>")  
																             );
		complexTextMessage.AddMessageContentItem(contentItem);
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendRichMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("富文本消息消息概要", ComplexMessageType.RichText);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这是富文本消息", 
																              "http://img03.mifile.cn/webfile/images/2014/cn/goods/headphone/specs-01.png",
																              Action.InstanceAsOpenHtml("<b>你好，富文本消息！！</b>")
																             );
		complexTextMessage.AddMessageContentItem(contentItem);
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendGridLinearMessage() {
		ComplexMessageContent complexMessageContent = new ComplexMessageContent("Grid线性消息概要", ComplexMessageType.Linear);
		
		TitleLinearComplexMessageContentItem title = new TitleLinearComplexMessageContentItem("Grid标题");
		TextLinearComplexMessageContentItem  hint  = new TextLinearComplexMessageContentItem("Grid提示");
		
		GridContent gridContent = new GridContent(2);
		gridContent.AddColumn(new Column("姓名", Alignment.Center, 40));
        gridContent.AddColumn(new Column("部门", Alignment.Left, 60));
		gridContent.AddData(new String[] {"张三","云应用平台部"});
		gridContent.AddData(new String[] {"李四","云应用体验部"});
		GridLinearComplexMessageContentItem grid = new GridLinearComplexMessageContentItem(gridContent);
		
		complexMessageContent.AddMessageContentItem(title).AddMessageContentItem(grid).AddMessageContentItem(hint);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendFormLinearMessage() {
		ComplexMessageContent complexMessageContent = new ComplexMessageContent("Form线性消息概要", ComplexMessageType.Linear);
		
		TitleLinearComplexMessageContentItem title = new TitleLinearComplexMessageContentItem("Form标题");
		TextLinearComplexMessageContentItem  hint  = new TextLinearComplexMessageContentItem("Hint提示");
		
		FormContent formContent = new FormContent(new FormFieldConfig(30,Alignment.Center), new FormFieldConfig(70, Alignment.Left));
		formContent.AddData("姓名", "张三");
		formContent.AddData("籍贯", "广州");
		formContent.AddData("年龄", "30");
		formContent.AddData("爱好", "唱歌、跳舞");
		FormLinearComplexMessageContentItem form = new FormLinearComplexMessageContentItem(formContent);
		
	    complexMessageContent.AddMessageContentItem(title).AddMessageContentItem(form).AddMessageContentItem(hint);
		
	    SendMessageResult result = _messageClient.SendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendOpenNativeFunctionMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个原生指令的Action消息", ComplexMessageType.Text);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开原生的聊天页面", 
				Action.InstanceAsOpenNative(NativeCommandAndParamsBuilder.BuildAsOpenNativeFunction("StartChat")));
		complexTextMessage.AddMessageContentItem(contentItem);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendOpenNativeFunctionMessageAsOpenBingoTouchLocalPage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个Bingotouch本地应用页面的Action消息", ComplexMessageType.Text);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开BingoTouch本地应用页面", 
				Action.InstanceAsOpenNative(NativeCommandAndParamsBuilder.BuildAsOpenBingoTouchLocalAppPage("BingoService", "/modules/leave/addleave.html")));
		complexTextMessage.AddMessageContentItem(contentItem);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendOpenNativeFunctionMessageAsOpenBingoTouchRemotePage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个Bingotouch远程页面的Action消息", ComplexMessageType.Text);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开BingoTouch远程应用页面", 
				Action.InstanceAsOpenNative(NativeCommandAndParamsBuilder.BuildAsOpenBingoTouchRemoteAppPage("http://www.mi.com")));
		complexTextMessage.AddMessageContentItem(contentItem);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendOpenUrlMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个远程URL的Action消息", ComplexMessageType.Text);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开远程URL页面", 
				Action.InstanceAsOpenUrl("http://www.xiaomi.com"));
		complexTextMessage.AddMessageContentItem(contentItem);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendOpenHtmlMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("演示打开一个HTML的Action消息", ComplexMessageType.Text);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("点击可以打开HTML页面", 
				Action.InstanceAsOpenHtml("<div><b>这里是HTML的详细内容</b></div>"));
		complexTextMessage.AddMessageContentItem(contentItem);
		
		SendMessageResult result = _messageClient.SendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.AreEqual(true, result.Success);
	}
	
	[TestMethod]
	public void TestSendMultiTextMessage() {
	    List<PersonMessageReceiver> personMessageReceivers = new List<PersonMessageReceiver>
	    {
	        new PersonMessageReceiver("zhongt", "钟涛"),
	        new PersonMessageReceiver("wuyang", "武扬")
	    };
	    MultiMessageReceiver multiMessageReceiver = new MultiMessageReceiver(personMessageReceivers, PersonMessageReceiverIdType.LoginId);
		SendMessageResult result = _messageClient.SendMultiMessage(new TextMessageContent("简单文本消息"), multiMessageReceiver);
		Assert.AreEqual(true, result.Success);
	}
    }
}
