package com.doume.max.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doume.max.dao.CreditDao;
import com.doume.max.dao.GoodsDao;
import com.doume.max.dao.OrdersDao;
import com.doume.max.entity.Business;
import com.doume.max.entity.BusinessComment;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Credit;
import com.doume.max.entity.Customer;
import com.doume.max.entity.Deal;
import com.doume.max.entity.Goods;
import com.doume.max.entity.Message;
import com.doume.max.entity.Orders;
import com.doume.max.entity.OrdersItem;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;

@Service
public class BusinessService  extends UserService{
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private CreditDao creditDao;
	@Autowired
	private GoodsDao goodsDao;
	
	public void register(User user,Business business) throws UserExistException
	{
		User usr = userDao.findByUserName(user.getUserName());
		if(usr!=null){
			throw new UserExistException("User is exist!");
		}else
		{
			user.setBusiness();
			userDao.save(user);
			
			business.setUserId(user.getUserId());
			business.getHome().setUserId(business.getUserId());
			business.getLocation().setUserId(business.getUserId());
			businessDao.save(business);
		}
	}
	public void update(Business business) throws UserExistException
	{
		Business db = businessDao.get(business.getUserId());
		if(db!=null&&db!=business){
			db.setAddr(business.getAddr());
			db.setbName(business.getbName());
			db.setHome(business.getHome());
			db.setInformation(business.getInformation());
			db.setLocation(business.getLocation());
			db.setPhoneno(business.getPhoneno());
			businessDao.update(db);
		}
	}

	public Customer addMember(Long businessId, String userName) {
		User usr = userDao.findByUserName(userName);
		if(usr!=null){
			Credit credit = new Credit();
			credit.setSellerId(businessId);
			credit.setBuyerId(usr.getUserId());
			credit.setValue(0);
			creditDao.addCredit(credit);
			return customerDao.get(usr.getUserId());
		}
		return null;
	}
	public List<Customer> getMembers(Long businessId) {
		List<Credit> credits = getCredits(businessId);
		return getMembers(credits);
	}
	/*
	 * TODO 信息屏蔽
	 */
	private List<Customer> getMembers(List<Credit> credits)
	{
		List<Long> ids = new LinkedList<Long>();
		for(Credit c:credits)
		{
			ids.add(c.getBuyerId());
		}
		System.out.println("getVipUser:ids---->" + ids);
		return customerDao.findByIds(ids.toArray(new Long[0]));
	}
	public void addMembers(Long userId,List<User> members) {
		List<Credit> credits = new ArrayList<Credit>(members.size());
		for(User usr:members)
		{
			Credit c = new Credit();
			c.setSellerId(userId);
			c.setBuyerId(usr.getUserId());
			c.setValue(0);
			credits.add(c);
		}
		addCredits(credits);
	}

	public List<Credit> getCredits(Long userId) {
		return creditDao.findBySellerId(userId);
	}
	public List<Credit> addCredits(List<Credit> credits) {
		List<Credit> result = new ArrayList<Credit>(credits.size());
		for(Credit credit:credits)
		{
			Credit rs = creditDao.addCredit(credit);
			result.add(rs);
		}
		return result;
	}
	public List<Goods> getOwnGoods(Long userId) {
		return goodsDao.find(new Long[]{userId}, Goods.ALL, null, null);
	}
	public boolean addGoods(String rootDir,Goods goods) {
		Business bus = businessDao.get(goods.getSellerId());
		if(bus!=null && bus.getCapacity()>bus.getUsed())
		{
	        goods.setMediaId(bus.getUserId()+"/" + bus.getUsed());
	        String filePath = rootDir +goods.getMediaId() ;
	        File file = new File(filePath);
	        try {
	        	file.getParentFile().mkdir();
	        	file.createNewFile();
	        	if(goods.getMedia()!=null)
	        	{
		        	goods.getMedia().transferTo(file);
	        	}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
	        bus.setUsed(bus.getUsed() + 1);
	        businessDao.update(bus);
	        goodsDao.save(goods);
	        return true;
		}
        return false;
	}
	public boolean updateGoods(String rootDir, Goods goods) {
		Business bus = businessDao.get(goods.getSellerId());
		Goods db = goodsDao.get(goods.getGoodsId());
		if(bus!=null&&db!=null&&db.getSellerId().equals(goods.getSellerId()))
		{
			goods.setMediaId(goods.getMediaId());
	        String filePath = rootDir +goods.getMediaId() ;
	        File file = new File(filePath);
	        try {
	        	file.getParentFile().mkdir();
	        	file.createNewFile();
	        	goods.getMedia().transferTo(file);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
	        goodsDao.save(goods);
	        return true;
		}
        return false;
	}
	public Orders subCredits(Credit credit)
	{
		Credit old = creditDao.find(credit.getSellerId(), credit.getBuyerId());
		if(old != null&&old.getValue() >= credit.getValue())
		{
			Orders orders = new Orders();
			orders.setBuyerId(credit.getBuyerId());
			orders.setSellerId(credit.getSellerId());
			orders.usingCredit();
			orders.addGoods(Goods.UNIT, credit.getValue());
			ordersDao.save(orders);
			return orders;
		}else
		{
			return null;
		}
	}
	
	public void acceptOrders(Long userId,Long ordersId) {
		Orders dbOrders = ordersDao.get(ordersId);
		if(dbOrders==null)return;
		if(!dbOrders.isCanceled())
		{
			dbOrders.accept();
			ordersDao.update(dbOrders);
			sendMessage(userId,dbOrders.getBuyerId(), Message.ACTUAL|Message.UNREAD,
					"accept:"+dbOrders.getOrdersInfo());
		}else
		{
			sendMessage(User.SYSTEM.getUserId(),dbOrders.getBuyerId(),Message.ACTUAL,
					"accept:"+dbOrders.getOrdersInfo());
		}
	}

	public void refuseOrders(Long userId,Long ordersId) {
		Orders dbOrders = ordersDao.get(ordersId);
		if(dbOrders==null)return;
		StringBuffer sb  = new StringBuffer(dbOrders.getOrdersInfo());
		for(OrdersItem o:dbOrders.getOrdersItem())
		{
			Goods goods = o.getGoods();
			sb.append(goods.getGoodsName() +" * " + o.getValue()+";");
			addFailedDeal(userId,dbOrders.getBuyerId(),goods.getGoodsId(),"Refuse:" + goods.getGoodsName() +" * " + o.getValue());
		}
		ordersDao.remove(dbOrders);
		sendMessage(userId,dbOrders.getBuyerId(),Message.ACTUAL|Message.UNREAD,
				"Refuse:\n" + sb.toString());
	}
	/*
	 * 需要收钱时 商家可以确认
	 */
	public void confirmOrders(Long userId,Long ordersId) {
		Orders dbOrders = ordersDao.get(ordersId);
		if(dbOrders==null)return;
		Credit credit = creditDao.find(userId, dbOrders.getBuyerId());
		if(credit==null)
		{
			credit = new Credit();
			credit.setSellerId(userId);
			credit.setBuyerId(dbOrders.getBuyerId());
			credit.setValue(0);
			credit = creditDao.addCredit(credit);
		}
		Integer curCredit = dbOrders.getTotalCredit();
		StringBuffer msgContent = new StringBuffer(dbOrders.getOrdersInfo());
		msgContent.append("CurrentCredit:"+ curCredit);
		msgContent.append("LastCredit:" + credit.getValue());
		if(curCredit>0)
		{
			for(OrdersItem o:dbOrders.getOrdersItem())
			{
				Goods goods = o.getGoods();
				goods.setSelledCount(goods.getSelledCount() + o.getValue());
				goodsDao.update(goods);
				addDeal(userId,dbOrders.getBuyerId(),goods.getGoodsId(),
						"Confrim:#[" + goods.getGoodsId() +"] * " + o.getValue());
				addGoodsComment(dbOrders.getBuyerId(),goods.getGoodsId(),null,null);
			}
			credit.setValue(credit.getValue() + curCredit);
			msgContent.append("TotalCredit:" + credit.getValue());
			creditDao.update(credit);
			ordersDao.remove(dbOrders);
			sendMessage(userId,dbOrders.getBuyerId(),Message.ACTUAL|Message.UNREAD,
					"ConfirmOrders:" + msgContent.toString());
		}else
		{
			sendMessage(userId,dbOrders.getBuyerId(),Message.ACTUAL|Message.UNREAD,
					"PermissionDenied:" + msgContent.toString());
		}
	}

	public Business getBusiness(Long userId) {
		return businessDao.get(userId);
	}
	public List<Orders> getOrders(Long sellerId) {
		return ordersDao.findBySeller(sellerId);
	}
	public void addOrders(Long sellerId,Orders orders){
		orders.setSellerId(sellerId);
		ordersDao.save(orders);
	}
	public List<Deal> getDeals(Long sellerId) {
		return dealDao.findBySellerId(sellerId);
	}
	public List<Goods> getGoods(Long sellerId) {
		return goodsDao.find(new Long[]{sellerId}, Goods.ALL, null, null);
	}
	public void removeSuggestion(Long userId, Long sugId) {
		Comment cmm = commentDao.get(sugId);
		if(cmm instanceof BusinessComment){
			if(cmm.getTagId().equals(userId)){
				commentDao.remove(cmm);
			}
		}
	}
}
