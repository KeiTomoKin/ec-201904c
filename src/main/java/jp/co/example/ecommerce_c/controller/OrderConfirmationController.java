package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.form.OrderConfirmationForm;
import jp.co.example.ecommerce_c.service.OrderConfirmationService;

/**
 * 注文確認画面を表示するクラス.
 *
 * @author takuya.aramaki
 */
@Controller
@RequestMapping("/confirm_order")
public class OrderConfirmationController {

	@Autowired
	private OrderConfirmationService orderConfirmationService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public OrderConfirmationForm setOrderCompleteForm() {
		return new OrderConfirmationForm();
	}

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

	@RequestMapping("/orderComplete")
	public String orderComplete(@Validated OrderConfirmationForm form, BindingResult result) {
		// 入力値チェック
		if (result.hasErrors()) {
			return "order_confirm";
		}

		// 注文情報の作成
		Order order = new Order();
		BeanUtils.copyProperties(form, order);
		order.setStutasByPaymentMethod();
		order.setDestinationName(form.getDestinationFirstName() + " " + form.getDestinationLastName());

		orderConfirmationService.orderComplete(order);

		return "order_finished";
	}
}
