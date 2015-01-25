package com.doume.max.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doume.max.cons.MUtils;
import com.doume.max.dao.AdministratorDao;
import com.doume.max.dao.BusinessDao;
import com.doume.max.dao.CommentDao;
import com.doume.max.dao.CustomerDao;
import com.doume.max.dao.DealDao;
import com.doume.max.dao.LoginLogDao;
import com.doume.max.dao.MessageDao;
import com.doume.max.dao.NewsDao;
import com.doume.max.dao.UserDao;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.Business;
import com.doume.max.entity.BusinessComment;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Customer;
import com.doume.max.entity.Deal;
import com.doume.max.entity.FailedDeal;
import com.doume.max.entity.GoodsComment;
import com.doume.max.entity.LoginLog;
import com.doume.max.entity.Message;
import com.doume.max.entity.News;
import com.doume.max.entity.NewsComment;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;

@Service("userService")
public class UserService {
	protected final static Logger logger = Logger.getLogger(UserService.class);
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected DealDao dealDao;
	@Autowired
	protected NewsDao newsDao;
	@Autowired
	protected AdministratorDao adminDao;
	@Autowired
	protected BusinessDao businessDao;
	@Autowired
	protected CustomerDao customerDao;
	@Autowired
	protected LoginLogDao loginLogDao;
	@Autowired
	protected MessageDao messageDao;
	@Autowired
	protected CommentDao commentDao;
	
	public void register(User user) throws UserExistException{
		User usr = userDao.findByUserName(user.getUserName());
		if(usr!=null){
			throw new UserExistException("User is exist!");
		}else
		{
			user.setPasswordMD5(user.getPassword());
			userDao.save(user);
			if(user.isAdmin()){
				Administrator admin = new Administrator();
				admin.setUserId(user.getUserId());
				adminDao.save(admin);
			}else if(user.isBusiness()){
				Business bus = new Business();
				bus.setUserId(user.getUserId());
				businessDao.save(bus);
			}else if(user.isCustomer()){
				Customer cus = new Customer();
				cus.setUserId(user.getUserId());
				customerDao.save(cus);
			}
		}
	}
	public User getUser(Long userId){
		return userDao.get(userId);
	}
	public User update(User user) throws UserExistException{
		User usr = userDao.get(user.getUserId());
		if(usr==null)return usr;
		if(!usr.getUserName().equals(user.getUserName()))
		{
			User ck_usr = userDao.findByUserName(user.getUserName());
			if(ck_usr!=null&&!ck_usr.getUserName().toLowerCase().equals(user.getUserName().toLowerCase())){
				throw new UserExistException("User is exist!");
			}else
			{
				usr.setUserName(user.getUserName());
			}
		}
		if (!usr.getPassword().equals(user.getPassword())) {
			usr.setPasswordMD5(user.getPassword());
		}
		userDao.update(usr);
		return usr;
	}

	public User loginByUser(String user,String password){
		User usr = loginByUserName(user,password);
		if(usr!=null)return usr;
		return loginByOpenId(user,password);
	}
	
	public User loginByUserName(String userName, String password) {
		User user = userDao.findByUserName(userName);
		if (user != null && !user.isLocked()
				&& user.getPassword().equals(MUtils.getMD5Code(password))) {
			return user;
		} else {
			return null;
		}
	}

	public User loginByOpenId(String openId, String password) {
		User user = userDao.findByOpenId(openId);
		if (user != null && !user.isLocked()
				&& user.getPassword().equals(MUtils.getMD5Code(password))) {
			return user;
		} else {
			return null;
		}
	}
	
	public LoginLog loginSuccess(User user){
		LoginLog loginLog = new LoginLog();
		loginLog.setUser(user);
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(new Date());
		userDao.update(user);
		loginLogDao.save(loginLog);
		return loginLog;
	}
	
	public List<User> getAllUsers() {
		return userDao.loadAll();
	}
	
	public List<Message> getActualMessage(Long userId)
	{
		return messageDao.findByReceiverMsgType(userId,Message.ACTUAL);
	}
		
	protected Message sendMessage(Long senderId,Long receiverId,Integer msgType,String content)
	{
		Message amsg = new Message();
		amsg.setSenderId(senderId);
		amsg.setReceiverId(receiverId);
		amsg.setMsgType(msgType);
		amsg.setMsgDate(new Date());
		amsg.setContent(content);
		messageDao.save(amsg);
		return amsg;
	}

	protected Deal addDeal(Long sellerId,Long buyerId,Long goodsId,String dealMsg)
	{
		Deal deal = new Deal();
		deal.setBuyerId(buyerId);
		deal.setSellerId(sellerId);
		deal.setGoodsId(goodsId);
		deal.setDealTime(new Date());
		deal.setDealMsg(dealMsg);
		dealDao.save(deal);
		return deal;
	}
	
	protected FailedDeal addFailedDeal(Long sellerId,Long buyerId,Long goodsId,String dealMsg)
	{
		FailedDeal deal = new FailedDeal();
		deal.setBuyerId(buyerId);
		deal.setSellerId(sellerId);
		deal.setGoodsId(goodsId);
		deal.setDealTime(new Date());
		deal.setDealMsg(dealMsg);
		dealDao.save(deal);
		return deal;
	}

	protected GoodsComment addGoodsComment(Long userId,Long tagId,Integer score,String content)
	{
		GoodsComment cmm = new GoodsComment();
		cmm.setCmmTime(new Date());
		cmm.setContent(content);
		cmm.setScore(score);
		cmm.setTagId(tagId);
		cmm.setUserId(userId);
		commentDao.save(cmm);
		return cmm;
	}
	protected NewsComment addNewsComment(Long userId,Long tagId,Integer score,String content)
	{
		NewsComment cmm = new NewsComment();
		cmm.setCmmTime(new Date());
		cmm.setContent(content);
		cmm.setScore(score);
		cmm.setTagId(tagId);
		cmm.setUserId(userId);
		commentDao.save(cmm);
		return cmm;
	}
	protected BusinessComment addBusinessComment(Long userId,Long tagId,Integer score,String content)
	{
		BusinessComment cmm = new BusinessComment();
		cmm.setCmmTime(new Date());
		cmm.setContent(content);
		cmm.setScore(score);
		cmm.setTagId(tagId);
		cmm.setUserId(userId);
		commentDao.save(cmm);
		return cmm;
	}
	public void sendMessage(Message msg){
		messageDao.save(msg);
	}
	public void removeMessage(Long userId, Long msgId)
	{
		Message msg = messageDao.get(msgId);
		if(msg!=null&&(msg.getReceiverId().equals(userId)||msg.getSenderId().equals(userId)))
		{
			messageDao.remove(msg);
		}
	}
	public void unregister(User usr) {
		if(usr.isAdmin()){
			Administrator admin = adminDao.get(usr.getUserId());
			if(admin!=null)adminDao.remove(admin);
		}else if(usr.isBusiness())
		{
			Business bus = businessDao.get(usr.getUserId());
			if(bus!=null)businessDao.remove(bus);
		}else if(usr.isCustomer())
		{
			Customer cus = customerDao.get(usr.getUserId());
			customerDao.remove(cus);
		}else
		{
			System.out.println("UserService....    unregister....");
		}
		if(usr != null)
			userDao.remove(usr);
	}

	public void getComment(Long cmmId) {
		commentDao.get(cmmId);
	}
	public void removeComment(Comment c) {
		commentDao.remove(c);
	}
	public List<Comment> getCommentByTargTblTypeTargetId(String tblType,Long targetId) {
		return commentDao.findByTarget(tblType, targetId);
	}
	public News getNews(Long newsId) {
		return newsDao.get(newsId);
	}
	public List<News> findNews(String condition){
		return newsDao.loadAll();
	}
}
