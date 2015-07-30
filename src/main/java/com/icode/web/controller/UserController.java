package com.icode.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icode.dto.UserDTO;
import com.icode.entity.User;
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

    @RequestMapping(value = "/user/{pkey}", method = RequestMethod.GET)
	public String hello(@PathVariable("pkey") Long pkey,ModelMap model) {

        
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Nadhiya");
        userDTO.setAge(16);
        
        userService.createUser(userDTO);
        
		model.addAttribute("user", userService.getUser(userDTO.getPkey()));

		return "user";

	}

}