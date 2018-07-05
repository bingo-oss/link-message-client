package link.message.client;

import org.junit.Test;

import junit.framework.TestCase;
import link.message.client.messager.MessageSender;

public class MessageTest extends TestCase {

	@Test
	public void testToJson() {
		Message m = new Message();
		MessageSender from = new MessageSender();
		from.setFromCompany("a");
		from.setFromId("a");
		from.setFromName("a");
		from.setFromType("1");
		m.setFrom(from);
		m.setToDeviceTypes("01,02");
		System.out.println(m.toJson());
	}
}