package com.doume.max.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.mortbay.jetty.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.doume.max.cons.CommonConstant;
import com.doume.max.entity.Business;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Credit;
import com.doume.max.entity.Customer;
import com.doume.max.entity.Deal;
import com.doume.max.entity.Goods;
import com.doume.max.entity.Message;
import com.doume.max.entity.Orders;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;
import com.doume.max.service.BusinessService;

@Controller
@RequestMapping("/business")
public class BusinessController extends BaseController {
	protected static final Logger logger = Logger.getLogger(BusinessController.class);
	@Autowired
	protected BusinessService  busService;
	private ModelAndView mvHome;
	private User host;
	private Business bus;
	@RequestMapping("/home")
	public ModelAndView home()
	{
		mvHome = new ModelAndView();
		mvHome.addObject("user", new User());
		mvHome.addObject("tarUser", new User());
		mvHome.addObject("tarBusiness", new Business());
		mvHome.setViewName("business/business");
		return mvHome;
	}
	@RequestMapping(value="/login")
	public ModelAndView login(HttpSession session,@Valid @ModelAttribute("user") User user,BindingResult bindingResult){
		System.out.println("user:"+user);
		if(bindingResult.hasErrors())
		{
			System.out.println("hasErrors()");
			return mvHome;
		}else
		{
			if(user.getUserName()!=null){
				host = busService.loginByUserName(user.getUserName(), user.getPassword());
			}else if(user.getOpenId()!=null)
			{
				host = busService.loginByOpenId(user.getUserName(), user.getPassword());/*此处有意为之，用户名和和OpenId都可以*/
			}
			if(host!=null)
			{
				bus = busService.getBusiness(host.getUserId());
				ModelAndView mav = new ModelAndView();
				session.setAttribute(CommonConstant.USER_ME, host);
				session.setAttribute(CommonConstant.BUSINESS_ME, bus);
				mav.addObject("ObjectModel",new Object[]{host,bus});
				return mav;
			}else{
				return mvHome;
			}
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		System.out.println("logout");
		session.removeAttribute(CommonConstant.USER_ME);
		return "forward:/index.jsp";
	}
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(HttpSession session,@Valid @ModelAttribute("host") User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return mvHome;
		}else{
			ModelAndView mav = new ModelAndView();
			try {
				host = busService.update(user);
				session.setAttribute(CommonConstant.USER_ME, host);
				mav.addObject("ObjectModel", user);
			} catch (UserExistException e) {
				mav.addObject("ObjectModel", "UserExistException");
			}
			return mav;
		}
	}
	@RequestMapping("/updateBusiness")
	public ModelAndView updateBusiness(HttpSession session,@Valid @ModelAttribute("bus") Business business,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return mvHome;
		}
		ModelAndView mav = new ModelAndView();
		try {
			busService.update(business);
			bus = business;
			session.setAttribute(CommonConstant.BUSINESS_ME, bus);
		} catch (UserExistException e) {
			e.printStackTrace();
			mav.addObject("ObjectModel",e);
		}
		return mav;
	}
	@RequestMapping(value="/updateGoods")
	public ModelAndView updateGoods(HttpServletRequest request,
			@Valid @ModelAttribute("goods") Goods goods) {
		ModelAndView mav = new ModelAndView();
		String rootDir = this.getAppbaseUrl(request, "/");
		goods.setSellerId(host.getUserId());
		if (goods.getGoodsId() == null) {
			if (busService.addGoods(rootDir, goods)) {
				mav.addObject("ObjectModel", HttpStatus.OK);
			} else {
				mav.addObject("ObjectModel", "Lack of Capacity!");
			}
		} else {
			if (busService.updateGoods(rootDir, goods)) {
				mav.addObject("ObjectModel", HttpStatus.OK);
			} else {
				mav.addObject("ObjectModel", HttpStatus.Expectation_Failed);
			}
		}
		return mav;
	}
	@RequestMapping(value="/members")
	public ModelAndView getMembers()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("ObjectModel",busService.getMembers(host.getUserId()));
		return mav;
	}
	@RequestMapping(value="/sendMessage")
	public ModelAndView sendMessage(Message msg)
	{
		ModelAndView mav = new ModelAndView();
		msg.setSenderId(host.getUserId());
		busService.sendMessage(msg);
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/removeMessage")
	public ModelAndView removeMessage(Long msgId)
	{
		ModelAndView mav = new ModelAndView();
		busService.removeMessage(host.getUserId(), msgId);
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/getOrders")
	public ModelAndView getOrders(){
		ModelAndView mav = new ModelAndView();
		List<Orders> orders = busService.getOrders(host.getUserId());
		mav.addObject("ObjectModel",orders);
		return mav;
	}
	@RequestMapping(value="/addOrders")
	public ModelAndView addOrders(Orders orders){
		ModelAndView mav = new ModelAndView();
		busService.addOrders(host.getUserId(),orders);
		List<Orders> rs = busService.getOrders(host.getUserId());
		mav.addObject("ObjectModel",rs);
		return mav;
	}
	@RequestMapping(value="/confrimOrders")
	public ModelAndView confirmOrders(Long ordersId){
		ModelAndView mav = new ModelAndView();
		busService.confirmOrders(host.getUserId(), ordersId);
		List<Orders> orders = busService.getOrders(host.getUserId());
		mav.addObject("ObjectModel",orders);
		return mav;
	}
	@RequestMapping(value="/refuseOrders")
	public ModelAndView refuseOrders(Long ordersId){
		ModelAndView mav = new ModelAndView();
		busService.refuseOrders(host.getUserId(), ordersId);
		List<Orders> orders = busService.getOrders(host.getUserId());
		mav.addObject("ObjectModel",orders);
		return mav;
	}
	@RequestMapping(value="/addCredits")
	public ModelAndView addCredits(List<Credit> credits){
		ModelAndView mav = new ModelAndView();
		for(Credit c:credits){
			c.setSellerId(host.getUserId());
		}
		busService.addCredits(credits);
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/subCredit")
	public ModelAndView subCredit(Credit credit){
		ModelAndView mav = new ModelAndView();
		credit.setSellerId(host.getUserId());
		Orders orders = busService.subCredits(credit);
		mav.addObject("ObjectModel",orders);
		return mav;
	}
	@RequestMapping(value="/addMember")
	public ModelAndView addMember(String userName){
		ModelAndView mav = new ModelAndView();
		Customer customer = busService.addMember(host.getUserId(), userName);
		mav.addObject("ObjectModel", customer);
		return mav;
	}
	@RequestMapping(value="/getDeals")
	public ModelAndView getDeals(){
		ModelAndView mav = new ModelAndView();
		List<Deal> deals= busService.getDeals(host.getUserId());
		mav.addObject("ObjectModel", deals);
		return mav;
	}
	@RequestMapping(value="/getGoods")
	public ModelAndView getGoods(){
		ModelAndView mav = new ModelAndView();
		List<Goods> goods= busService.getGoods(host.getUserId());
		mav.addObject("ObjectModel", goods);
		return mav;
	}
	@RequestMapping(value="/getSuggestion")
	public ModelAndView getSuggestion(){
		ModelAndView mav = new ModelAndView();
		List<Comment> sugg= busService.getCommentByTargTblTypeTargetId("BusinessComment", host.getUserId());
		mav.addObject("ObjectModel", sugg);
		return mav;
	}
	@RequestMapping(value="/removeSuggestion")
	public ModelAndView removeSuggestion(Long sugId){
		ModelAndView mav = new ModelAndView();
		busService.removeSuggestion(host.getUserId(),sugId);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
}
