package com.icode.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.icode.service.UserService;

/**
 * 
 * @author nadhiya
 *
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
	    logger.info("check logging nadhiya");
		model.addAttribute("message", "Intro :)");
		return "user";

	}

	@SuppressWarnings("deprecation")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String hello(@PathVariable("id") int id,ModelMap model) {

//		ModelAndView model = new ModelAndView();
//		model.setViewName("user");
		model.addObject("user", userService.getUser(id));

		return "user";

	}

}