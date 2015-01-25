package com.doume.max.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.maven.shared.utils.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.doume.max.dao.AdministratorDao;
import com.doume.max.dao.GoodsDao;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.Business;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Goods;
import com.doume.max.entity.News;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;

@Service
public class AdministratorService extends UserService{

	@Autowired
	private AdministratorDao adminDao;
	@Autowired
	private GoodsDao goodsDao;
	
	public void register(User user,Administrator admin) throws UserExistException
	{
		User usr = userDao.findByUserName(user.getUserName());
		if(usr!=null){
			throw new UserExistException("User is exist!");
		}else
		{
			user.setUserType(User.ADMIN);
			userDao.save(user);
			admin.setUserId(user.getUserId());
			adminDao.save(admin);
		}
	}
	public Administrator getAdmin(Long id)
	{
		return adminDao.get(id);
	}
	public Business getBusiness(Long userId){
		return businessDao.get(userId);
	}
	public void addBusiness(Administrator admin,Business business)
	{
		admin.addBusiness(business);
		adminDao.update(admin);
		this.addDeal(User.SYSTEM.getUserId(), admin.getUserId(), Goods.UNIT.getGoodsId(), "addBusiness");
	}
	public Business updateBusiness(Administrator admin,Business newData)
	{
		Business oldData = businessDao.get(newData.getUserId());
		if(oldData != null && !oldData.equals(newData))
		{
			oldData.setAddr(newData.getAddr());
			oldData.setHome(newData.getHome());
			oldData.setBalance(newData.getBalance());
			oldData.setbType(newData.getbType());
			oldData.setPpm(newData.getPpm());
			oldData.setPhoneno(newData.getPhoneno());
			oldData.setInformation(newData.getInformation());
			oldData.setCapacity(newData.getCapacity());
			//oldData.setUsed(newData.getUsed());
			oldData.setLocation(newData.getLocation());
			oldData.setWeight(newData.getWeight());
			businessDao.update(oldData);
			addDeal(admin.getUserId(), oldData.getUserId(), Goods.UNIT.getGoodsId(),
					oldData.authorityInfo() + " --> " + newData.authorityInfo());
			return oldData;
		}
		return null;
	}
	public void payBusiness(Administrator admin,Long bId,Integer money)
	{
		Business bus = businessDao.get(bId);
		if(bus != null) {
			bus.setBalance(bus.getBalance() + money);
			businessDao.update(bus);
			this.addDeal(admin.getUserId(), bId, Goods.UNIT.getGoodsId(), "businessPay:" + money);
		}
	}
	public void lockBusiness(Administrator admin,Long bId,String reason)
	{
		Business bus = businessDao.get(bId);
		if(bus != null) {
			bus.lock();
			businessDao.update(bus);
			this.addDeal(admin.getUserId(), bId, Goods.UNIT.getGoodsId(), "lock:" + reason);
		}
	}
	public void unlockBusiness(Administrator admin,Long bId,String reason)
	{
		Business bus = businessDao.get(bId);
		if(bus != null) {
			bus.unlock();
			businessDao.update(bus);
			this.addDeal(admin.getUserId(), bId, Goods.UNIT.getGoodsId(), "unlock:" + reason);
		}
	}
	public void deleteComment(Administrator admin,Long cmmId){
		Comment cmm = commentDao.get(cmmId);
		if(cmm!=null)
		{
			commentDao.remove(cmm);
		}
	}
	public List<Goods> searchGoods(String conditions) {
		return goodsDao.findByNameLike("%"+conditions+"%");
	}
	private String saveImage(CommonsMultipartFile cmf,String root,String identify){
		if(cmf==null){
	        String mediaId = identify + ".png";
	        File defaultGoods = new File(root+"/images/0/goods.png");
	        File media = new File(root + "/images/" + mediaId);
	        try {
				FileUtils.copyFile(defaultGoods, media);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return mediaId;
		}else{
			String ofn =cmf.getOriginalFilename();
			String mediaId = identify+ ofn.substring(ofn.lastIndexOf('.'));
	        String filePath = root + "/images/" +mediaId;
	        File file = new File(filePath);
	        try {
	        	file.getParentFile().mkdir();
	        	file.createNewFile();
	        	cmf.transferTo(file);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return null;
	        }
	        return mediaId;
		}
	}
	public Goods uploadGoods(String rootDir,Goods goods) {
		Business bus = businessDao.get(goods.getSellerId());
		if(bus!=null && bus.getCapacity()>bus.getUsed()){
			String mediaId = saveImage(goods.getMedia(),rootDir,bus.getUserId()+"/" + bus.getUsed());
	        goods.setMediaId(mediaId);
	        bus.setUsed(bus.getUsed() + 1);
	        businessDao.save(bus);
	        goodsDao.save(goods);
	        return goods;
		}
        return null;
	}
	public Goods updateGoods(String rootDir,Goods goods) {
		Business bus = businessDao.get(goods.getSellerId());
		Goods db = goodsDao.get(goods.getGoodsId());
		if(bus!=null&&db!=null&&db.getSellerId().equals(goods.getSellerId())){
			db.setCreditPrice(goods.getCreditPrice());
			db.setDescription(goods.getDescription());
			db.setGoodsName(goods.getGoodsName());
			db.setGoodsType(goods.getGoodsType());
			db.setPrice(goods.getPrice());
			db.setRetCredit(goods.getRetCredit());
			db.setSellerId(goods.getSellerId());
			if(goods.getMedia()!=null){
		        String filePath = rootDir + db.getMediaId();
		        File file = new File(filePath);
		        try {
		        	goods.getMedia().transferTo(file);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	return null;
		        }
			}
	        goodsDao.update(db);
	        return db;
		}
        return null;
	}
	public void putNews(String rootDir, News news) {
		if(news.getNewsDate()==null){
			news.setNewsDate(new Date());
		}
		newsDao.save(news);
		String filePath = rootDir + news.getNewsId();
		File file = new File(filePath);
		try {
			file.getParentFile().mkdir();
			file.createNewFile();
			news.getFile().transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removeNews(Long newsId) {
		newsDao.remove(newsDao.get(newsId));
		commentDao.removeByTarget("News", newsId);
	}
	public Goods getGoods(Long goodsId) {
		return goodsDao.get(goodsId);
	}
}
