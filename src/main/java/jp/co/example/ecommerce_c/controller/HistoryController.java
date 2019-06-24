package jp.co.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.service.HistoryService;

/**
 * 注文履歴を出力するためのコントローラー.
 * 
 * @author takara.miyazaki
 *
 */
@Controller
@RequestMapping("/history")
public class HistoryController {
	
	@Autowired
	HistoryService historyService;
	
	/**
	 * 注文履歴画面を出力.
	 * 
	 * @param model リクエストスコープ
	 * @param loginUser ログインしているユーザ情報
	 * @return 注文履歴画面
	 */
	@RequestMapping("/showHistoryList")
	public String showHistoryList(Model model,  @AuthenticationPrincipal LoginUser loginUser) {
		int userId = loginUser.getUser().getId();
		List<Order> orderList = historyService.getOrderList(userId);
		model.addAttribute("orderList", orderList);
		
		return "history_list";
		
	}

}
