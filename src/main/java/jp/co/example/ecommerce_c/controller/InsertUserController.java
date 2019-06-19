package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.InsertUserForm;
import jp.co.example.ecommerce_c.service.UserService;

@Controller
@RequestMapping("/registration")
public class InsertUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String toInsert() {
		return "register_user";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertUserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userService.insert(user);
		
		return "login";
	}
}
