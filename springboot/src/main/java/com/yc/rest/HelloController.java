package com.yc.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yc.entity.CommentDto;
import com.yc.entity.User;
import com.yc.service.CommentService;
import com.yc.service.UserService;

@Controller
public class HelloController {
	
	@Autowired
	private UserService userServie;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/index")
	public ModelAndView hello(){
		ModelAndView mav = new ModelAndView();
		List<User> list = userServie.findAll();
		Integer totalCounts = userServie.getTotalCounts();
		mav.addObject("now", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
		List<CommentDto> commentList = commentService.findAll();
		Map<String,Object> map = new HashMap<>();
		map.put("totalCounts", totalCounts);
		map.put("list", list);
		mav.addObject("map", map);
		mav.addObject("commentList", commentList);
		mav.setViewName("hello");
		return mav;
	}
	@RequestMapping("/hello")
	public String login(){
		return "login";
	}
}
