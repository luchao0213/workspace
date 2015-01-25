package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Message;

public interface MessageDao extends BaseDao<Message>{
	public List<Message> findByReceiverMsgType(Long receiverId,Integer msgType);
	public List<Message> findByReceiver(Long receiverId);
	public List<Message> findBySender(Long senderId);
}
