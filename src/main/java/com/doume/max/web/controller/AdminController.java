package com.doume.max.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.mortbay.jetty.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.doume.max.cons.CommonConstant;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.Business;
import com.doume.max.entity.Goods;
import com.doume.max.entity.Message;
import com.doume.max.entity.News;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;
import com.doume.max.service.AdministratorService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	protected static final Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	protected AdministratorService adminService;
	private User host;
	private Administrator admin;
	@RequestMapping(value="/getLogin",method=RequestMethod.GET)
	public ModelAndView getLogin(){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		user.setUserId(0L);
		mav.addObject("user", user);
		mav.setViewName("/admin/login");
		return mav;
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpSession session,@Valid @ModelAttribute("user") User user,BindingResult bindingResult){
		ModelAndView mav = new ModelAndView();
		host = adminService.loginByUser(user.getUserName(), user.getPassword());/* 此处有意为之，用户名和和OpenId都可以 */
		if (host != null) {
			adminService.loginSuccess(host);
			admin = adminService.getAdmin(host.getUserId());
			session.setAttribute(CommonConstant.USER_ME, host);
			mav.addObject("user", host);
			mav.addObject("ObjectModel", admin.getBlist());
			mav.setViewName("/admin/home");
		} else {
			mav.addObject("user", user);
			mav.setViewName("/admin/login");
		}
		return mav;
	}
	@RequestMapping(value="/getHome",method=RequestMethod.GET)
	public ModelAndView getHome(HttpSession session){
		ModelAndView mav = new ModelAndView();
		host = (User)session.getAttribute(CommonConstant.USER_ME);
		if(host!=null){
			mav.addObject("ObjectModel", admin.getBlist());
			mav.setViewName("/admin/home");	
		}else {
			mav.addObject("user",new User());
			mav.setViewName("/admin/login");
		}
		return mav;
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(CommonConstant.USER_ME);
		return "forward:/index.jsp";
	}
	@RequestMapping(value="/getPutUser/{userId}",method=RequestMethod.GET)
	public ModelAndView getPutUser(@PathVariable("userId") Long userId){
		ModelAndView mav = new ModelAndView();
		User u =  null;
		if(userId!=0){
			u = adminService.getUser(userId);
		}
		if(u==null){
			u = new User();
			u.setUserId(0L);
		}
		mav.addObject("tarUser", u);
		mav.setViewName("/admin/putUser");
		return mav;
	}
	@RequestMapping(value="/putUser",method=RequestMethod.POST)
	public ModelAndView putUser(@Valid @ModelAttribute("tarUser") User user,
			@RequestParam Boolean locked, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		try {
			if (!bindingResult.hasErrors()) {
				if (locked) {
					adminService.lockBusiness(admin, user.getUserId(), "");
				} else {
					adminService.unlockBusiness(admin, user.getUserId(), "");
				}
				if (user.getUserId().equals(0L)) {
					adminService.register(user);
					Business business= new Business();
					business.setUserId(user.getUserId());
					mav.addObject("tarBusiness", business);
					mav.setViewName("/admin/updateBusiness");
					return mav;
				} else {
					System.out.println("update:" + user);
					user = adminService.update(user);
				}
				mav.addObject("tarUer", user);
			}
		} catch (UserExistException e) {
			ObjectError error = new ObjectError("userName",
					"UserExistException");
			bindingResult.addError(error);
		}
		return mav;
	}
	@RequestMapping(value="/getUpdateBusiness/{userId}",method=RequestMethod.GET)
	public ModelAndView getUpdateBusiness(@PathVariable("userId") Long userId){
		ModelAndView mav = new ModelAndView();
		if(userId !=0 ){
			Business b = adminService.getBusiness(userId);
			if(b==null)b = new Business();
			mav.addObject("tarBusiness", b);
		}else{
			mav.addObject("tarBusiness", new Business());
		}
		mav.setViewName("/admin/updateBusiness");
		return mav;
	}
	@RequestMapping(value="/updateBusiness",method=RequestMethod.POST)
	public ModelAndView updateBusiness(@Valid @ModelAttribute("tarBusiness") Business business,BindingResult bindingResult){
		ModelAndView mav = new ModelAndView();
		if(!bindingResult.hasErrors()){
			Business b = adminService.updateBusiness(admin, business);
			if(b!=null)business = b;
		}
		mav.addObject("tarBusiness", business);
		return mav;
	}
	@RequestMapping(value="/getGoods",method=RequestMethod.GET)
	public ModelAndView getGoods(String goodsSearch){
		ModelAndView mav = new ModelAndView();
		List<Goods>  result = adminService.searchGoods(goodsSearch);
		mav.addObject("ObjectModel", result);
		mav.setViewName("/admin/goods");
		return mav;
	}
	@RequestMapping(value="/getPutGoods/{goodsId}",method=RequestMethod.GET)
	public ModelAndView getPutGoods(@PathVariable("goodsId") Long goodsId){
		ModelAndView mav = new ModelAndView();
		Goods goods = adminService.getGoods(goodsId);
		if(goods == null){
			goods = new Goods();
			goods.setGoodsId(0L);
		}
		mav.addObject("ObjectModel", goods);
		mav.setViewName("/admin/putGoods");
		return mav;
	}
	@RequestMapping(value="/putGoods",method=RequestMethod.POST)
	public ModelAndView putGoods(@Valid @ModelAttribute("ObjectModel") Goods goods,
			BindingResult bindingResult)
	{
		ModelAndView mav = new ModelAndView();
		if(!bindingResult.hasErrors()){
			String rootDir= rpe.getRealPath("");
			if(goods.getGoodsId()!=null&&!goods.getGoodsId().equals(0L)){
				goods = adminService.updateGoods(rootDir,goods);
			}else{
				goods = adminService.uploadGoods(rootDir,goods);
			}
		}
		if(goods==null){
			goods = new Goods();
		}
		mav.addObject("ObjectModel", goods);
		return mav;
	}
	@RequestMapping(value="/getNews",method=RequestMethod.GET)
	public ModelAndView getNews(String keyword){
		ModelAndView mav = new ModelAndView();
		List<News> news = adminService.findNews(keyword);
		mav.addObject("ObjectModel", news);
		mav.setViewName("/admin/news");
		return mav;
	}
	@RequestMapping(value="/getPutNews/{newsId}",method=RequestMethod.GET)
	public ModelAndView getPutNews(@PathVariable("newsId") Long newsId){
		ModelAndView mav = new ModelAndView();
		News news = adminService.getNews(newsId);
		if(news==null){
			news = new News();
		}
		mav.addObject("ObjectModel", news);
		mav.setViewName("/admin/putNews");
		return mav;
	}
	@RequestMapping(value="/putNews")
	public ModelAndView putNews(News news)
	{
		ModelAndView mav = new ModelAndView();
		String rootDir= rpe.getRealPath("");
		adminService.putNews(rootDir,news);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/removeNews")
	public ModelAndView removeNews(Long newsId)
	{
		ModelAndView mav = new ModelAndView();
		adminService.removeNews(newsId);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/members")
	public ModelAndView getMembers()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("ObjectModel",admin.getBlist());
		return mav;
	}
	@RequestMapping(value="/searchGoods")
	public ModelAndView searchGoods(String conditions){
		ModelAndView mav = new ModelAndView();
		mav.addObject("ObjectModel",adminService.searchGoods(conditions));
		return mav;
	}
	@RequestMapping(value="/getCommentByGoodsId")
	public ModelAndView getCommentByGoods(Long goodsId){
		ModelAndView mav = new ModelAndView();
		mav.addObject("ObjectModel",adminService.getCommentByTargTblTypeTargetId("GoodsComment",goodsId));
		return mav;
	}
	@RequestMapping(value="/deleteComment")
	public ModelAndView deleteComment(Long cmmId)
	{
		adminService.deleteComment(admin, cmmId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
	//TODO
	@RequestMapping(value="/sendMessage")
	public ModelAndView sendMessage(String receivers,String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/sendMessage");
		if(receivers==null||content==null)return mav;
		if(receivers.equals("AllUsers")){
			Message msg = new Message();
			msg.setMsgDate(new Date());
			msg.setSenderId(host.getUserId());
			msg.setContent(content);
			msg.setMsgType(Message.TOUSER);
			adminService.sendMessage(msg);
		}else  if(receivers.equals("AllBusienss")){
			Message msg = new Message();
			msg.setMsgDate(new Date());
			msg.setSenderId(host.getUserId());
			msg.setContent(content);
			msg.setMsgType(Message.TOBUSINESS);
			adminService.sendMessage(msg);
		}else if(receivers.matches("[0-9,]*")){
			String[] ss = receivers.split(",");
			for(String sid:ss){
				Message msg = new Message();
				msg.setMsgDate(new Date());
				msg.setSenderId(host.getUserId());
				msg.setReceiverId(Long.valueOf(sid));
				msg.setContent(content);
				adminService.sendMessage(msg);
			}
		}
		return mav;
	}
	@RequestMapping(value="/removeMessage/{msgId}")
	public ModelAndView removeMessage(@PathVariable Long msgId)
	{
		ModelAndView mav = new ModelAndView();
		adminService.removeMessage(host.getUserId(), msgId);
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
}
