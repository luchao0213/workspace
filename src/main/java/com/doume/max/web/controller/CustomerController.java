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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.doume.max.cons.CommonConstant;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Credit;
import com.doume.max.entity.Customer;
import com.doume.max.entity.Deal;
import com.doume.max.entity.Message;
import com.doume.max.entity.Orders;
import com.doume.max.entity.SearchItem;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;
import com.doume.max.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
	protected static final Logger logger = Logger
			.getLogger(CustomerController.class);
	@Autowired
	protected CustomerService cusService;
	private ModelAndView mvHome;
	private User host;
	private Customer cus;

	@RequestMapping("/home")
	public ModelAndView home() {
		mvHome = new ModelAndView();
		mvHome.addObject("user", new User());
		mvHome.setViewName("customer/customer");
		return mvHome;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpSession session,
			@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult) {
		System.out.println("user:" + user);
		if (bindingResult.hasErrors()) {
			System.out.println("hasErrors()");
			return mvHome;
		} else {
			if (user.getUserName() != null) {
				host = cusService.loginByUserName(user.getUserName(),
						user.getPassword());
			} else if (user.getOpenId() != null) {
				host = cusService.loginByOpenId(user.getUserName(),
						user.getPassword());/* 此处有意为之，用户名和和OpenId都可以 */
			}
			if (host != null) {
				cus = cusService.getCustomer(host.getUserId());
				ModelAndView mav = new ModelAndView();
				session.setAttribute(CommonConstant.USER_ME, host);
				session.setAttribute(CommonConstant.CUSTOMER_ME, cus);
				mav.addObject("ObjectModel", new Object[] { host, cus });
				return mav;
			} else {
				return mvHome;
			}
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.removeAttribute(CommonConstant.USER_ME);
		return "forward:/index.jsp";
	}
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(HttpSession session,
			@Valid @ModelAttribute("host") User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return mvHome;
		} else {
			ModelAndView mav = new ModelAndView();
			try {
				host = cusService.update(user);
				session.setAttribute(CommonConstant.USER_ME, host);
				mav.addObject("ObjectModel", user);
			} catch (UserExistException e) {
				mav.addObject("ObjectModel", "UserExistException");
			}
			return mav;
		}
	}

	@RequestMapping("/updateCustomer")
	public ModelAndView updateCustomer(HttpSession session,
			@Valid @ModelAttribute("bus") Customer customer,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return mvHome;
		}
		ModelAndView mav = new ModelAndView();
		cusService.update(customer);
		cus = customer;
		session.setAttribute(CommonConstant.CUSTOMER_ME, cus);
		return mav;
	}

	@RequestMapping(value = "/sendMessage")
	public ModelAndView sendMessage(Message msg) {
		ModelAndView mav = new ModelAndView();
		msg.setSenderId(host.getUserId());
		cusService.sendMessage(msg);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}

	@RequestMapping(value = "/removeMessage")
	public ModelAndView removeMessage(Long msgId) {
		ModelAndView mav = new ModelAndView();
		cusService.removeMessage(host.getUserId(), msgId);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
	
	@RequestMapping(value="/addOrders")
	public ModelAndView addOrders(Orders orders){
		ModelAndView mav = new ModelAndView();
		cusService.addOrders(host.getUserId(), orders);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/getOrders")
	public ModelAndView getOrders(){
		ModelAndView mav = new ModelAndView();
		List<Orders> orders = cusService.getOrders(host.getUserId());
		mav.addObject("ObjectModel", orders);
		return mav;
	}
	@RequestMapping(value="/confirmOrders")
	public ModelAndView confirmOrders(Long ordersId){
		ModelAndView mav = new ModelAndView();
		Credit credit = cusService.confirmOrders(host.getUserId(), ordersId);
		mav.addObject("ObjectModel", credit);
		return mav;
	}
	@RequestMapping(value="/refuseOrders")
	public ModelAndView refuseOrders(Long ordersId){
		ModelAndView mav = new ModelAndView();
		cusService.refuseOrders(host.getUserId(), ordersId);
		mav.addObject("ObjectModel", HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/cancelOrders")
	public ModelAndView cancelOrders(Long ordersId){
		ModelAndView mav = new ModelAndView();
		Orders orders = cusService.cancelOrders(host.getUserId(), ordersId);
		mav.addObject("ObjectModel", orders);
		return mav;
	}
	@RequestMapping(value="/getCredits")
	public ModelAndView getCredits(Long ordersId){
		ModelAndView mav = new ModelAndView();
		List<Credit> credits = cusService.getCredits(host.getUserId());
		mav.addObject("ObjectModel", credits);
		return mav;
	}
	/*
	 * TODO
	 */
	@RequestMapping(value="/addEnshrines")
	public ModelAndView addEnshrines(SearchItem item)
	{
		ModelAndView mav = new ModelAndView();
		List<SearchItem> result = cusService.getEnshrine(host.getUserId());
		mav.addObject("ObjectModel", result);
		return mav;
	}
	/*
	 * TODO
	 */
	@RequestMapping(value="/getEnshrines")
	public ModelAndView getEnshrines()
	{
		ModelAndView mav = new ModelAndView();
		List<SearchItem> result = cusService.getEnshrine(host.getUserId());
		mav.addObject("ObjectModel", result);
		return mav;
	}
	@RequestMapping(value="/getEmptyComment")
	public ModelAndView getEmptyComment()
	{
		ModelAndView mav = new ModelAndView();
		List<Comment> result = cusService.getEmptyComment(host.getUserId());
		mav.addObject("ObjectModel", result);
		return mav;
	}
	@RequestMapping(value="/uploadComment")
	public ModelAndView uploadComment(Comment cmm)
	{
		ModelAndView mav = new ModelAndView();
		cusService.uploadComment(cmm);
		mav.addObject("ObjectModel",HttpStatus.OK);
		return mav;
	}
	@RequestMapping(value="/getDeals")
	public ModelAndView getDeals(Date date)
	{
		ModelAndView mav = new ModelAndView();
		List<Deal>  deals = cusService.getDeals(host.getUserId(),date);
		mav.addObject("ObjectModel",deals);
		return mav;
	}
}
