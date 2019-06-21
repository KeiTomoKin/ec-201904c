package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.service.OrderConfirmationService;

/**
 * 注文確認画面を表示するクラス.
 *
 * @author takuya.aramaki
 */
@Controller
@RequestMapping("confirm_order")
public class OrderConfirmationController {
	@Autowired
	private OrderConfirmationService orderConfirmationService;

	@Autowired
	private HttpSession session;

	/**
	 * 注文確認画面を表示します.
	 *
	 * @param model リクエストスコープ
	 * @return 注文確認画面へフォワード
	 */
	@RequestMapping("")
	public String confirmOrder(Model model) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		Order order = orderConfirmationService.getOrder(orderId);
		model.addAttribute("order", order);

		return "order_confirm";
	}
}
