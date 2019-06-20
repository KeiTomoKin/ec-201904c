package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ログイン用コントローラー.
 * 
 * @author takara.miyazaki
 *
 */
@Controller
@RequestMapping("")
public class LoginController {

	//ログイン処理はSpringSecurityで行うためコメントアウト)
//	@Autowired
//	private UserService userService;
//	
//	@ModelAttribute
//	public LoginForm setUpLoginForm() {
//		return new LoginForm();
//	}
	
	/**
	 * ログイン画面を出力.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/toLogin")
	public String toLogin(Model model,@RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		return "login";
	}
	
	/**
	 * ログイン処理.(SpringSecurityで行うためコメントアウト)
	 * 
	 * @param form ログインフォーム
	 * @return アイテムリスト画面
	 */
//	@RequestMapping("/login")
//	public String login(LoginForm form) {
//		System.out.println(form);
//		User user = userService.login(form.getEmail(), form.getPassword());
//		System.out.println(user);
//		if(user == null) {
//			return "/toLogin";
//		}
//		return "item_list";
//	}

}
