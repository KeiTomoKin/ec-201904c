package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウト用コントローラー.
 * 
 * @author takara.miyazaki
 *
 */
@Controller
@RequestMapping("")
public class LogoutController {
	
	/**
	 * ログアウト処理.(SpringSecurityで行うためコメントアウト)
	 * 
	 * @return ログイン画面へのリダイレクト
	 */
//	@RequestMapping("/logout")
//	public String logout() {
//		return "redirect:toLogin";
//	}

}
