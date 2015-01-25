package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Message;
import com.doume.max.dao.MessageDao;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao{
	private final String QUERY_BY_RECEIVER = "from Message where receiverId = ?";
	private final String QUERY_BY_SENDER = "from Message where senderId = ?";
	private final String QUERY_BY_MSGTYPE = "from Message where receiverId = ? and bitand(msgType,?)!=0";
	
	@Override
	public List<Message> findByReceiverMsgType(Long receiverId,Integer msgType) {
		return find(QUERY_BY_MSGTYPE,receiverId,msgType);
	}

	@Override
	public List<Message> findByReceiver(Long receiverId) {
		return find(QUERY_BY_RECEIVER,receiverId);
	}

	@Override
	public List<Message> findBySender(Long senderId) {
		return find(QUERY_BY_SENDER,senderId);
	}
}
