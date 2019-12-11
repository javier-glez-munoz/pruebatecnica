package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	public AppController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/loginuser")
	public String loginuser(){
		return "loginuser.jsp";
	}
	
	@RequestMapping("/listitems")
	public String listitems(){
		return "listitems.jsp";
	}
	
	@RequestMapping("/additem")
	public String additem(){
		return "additem.jsp";
	}
	@RequestMapping("/itemdetails")
	public String itemdetails(){
		return "itemdetails.jsp";
	}
}
