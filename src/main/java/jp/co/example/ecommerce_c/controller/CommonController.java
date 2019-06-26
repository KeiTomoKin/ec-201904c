package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 500エラー時にエラーページを表示するコントローラー.
 * 
 * @author koichi.nagata
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	/**
	 * エラー画面に遷移する.
	 * 
	 * @return 遷移先画面
	 */
	@RequestMapping("/maintenance")
	public String maintenance() {
		return "common/error";
	}
}
