package com.yc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.entity.User;
import com.yc.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(User user){
		return userService.verifyByName(user);
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "edit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(User user){
		userService.updateUserById(user);
		return "success";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id){
		userService.removeUserById(id);
		return "redirect:/index";
	}
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	@RequestMapping("/add")
	public String add(User user){
		userService.add(user);
		return "redirect:/index";
	}
}
