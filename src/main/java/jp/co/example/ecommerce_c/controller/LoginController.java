package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.LoginForm;
import jp.co.example.ecommerce_c.service.UserService;

/**
 * ログイン用コントローラー.
 * 
 * @author takara.miyazaki
 *
 */
@Controller
@RequestMapping("")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面へ移行.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * ログイン処理.
	 * 
	 * @param form ログインフォーム
	 * @return アイテムリスト画面
	 */
	@RequestMapping("/login")
	public String login(LoginForm form) {
		System.out.println(form);
		User user = userService.login(form.getEmail(), form.getPassword());
		System.out.println(user);
		if(user == null) {
			return toLogin();
		}
		return "item_list";
	}

}
