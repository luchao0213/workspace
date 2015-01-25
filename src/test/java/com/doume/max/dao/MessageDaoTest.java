package com.doume.max.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Message;
import com.doume.max.dao.MessageDao;

public class MessageDaoTest extends BaseDaoTest{
	@SpringBean("messageDao")
	private static MessageDao dao;
	private static Message[] data;

	@BeforeClass
	public static void initData() {
		data = new Message[4];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = new Message();
			Message msg = data[i];
			msg.setMsgId(i + 1L);
			msg.setSenderId(i%2+1L);
			msg.setReceiverId(i%3 + 1L);
			msg.setUnread();
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (Message d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (Message d : data) {
			dao.remove(d);
		}
	}
	@Test
	public void findByReceiverMsgType()
	{
		List<Message> rs = dao.findByReceiverMsgType(1L,Message.UNREAD);
		Assert.assertTrue(rs.size()==2);
		Message msg = rs.get(0);
		msg.setRead();
		dao.update(msg);
		rs=  dao.findByReceiverMsgType(1L,Message.UNREAD);
		Assert.assertTrue(rs.size()==1);
	}
	@Test
	public void findByReceiver(){
		List<Message> rs =  dao.findByReceiver(1L);
		Assert.assertTrue(rs.size()==2);
		rs =  dao.findByReceiver(2L);
		Assert.assertTrue(rs.size()==1);
	}
	@Test
	public void findBySender(){
		List<Message> rs = dao.findBySender(1L);
		Assert.assertTrue(rs.size()==2);
	}
}
