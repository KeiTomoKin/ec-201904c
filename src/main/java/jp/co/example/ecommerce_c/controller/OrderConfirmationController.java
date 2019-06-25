package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.User;
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
	public OrderConfirmationForm setOrderCompleteForm(@AuthenticationPrincipal LoginUser loginUser) {
		OrderConfirmationForm orderConfirmationForm = new OrderConfirmationForm();
		User user = loginUser.getUser();
		String[] userName = user.getName().split(" ");
		String[] userTel = user.getTelephone().split("-");

		orderConfirmationForm.setDestinationFirstName(userName[0]);
		orderConfirmationForm.setDestinationLastName(userName[1]);
		orderConfirmationForm.setDestinationEmail(user.getEmail());
		orderConfirmationForm.setDestinationZipcode(user.getZipcode());
		orderConfirmationForm.setDestinationAddress(user.getAddress());
		orderConfirmationForm.setDestinationFirstTel(userTel[0]);
		orderConfirmationForm.setDestinationMiddleFirstTel(userTel[1]);
		orderConfirmationForm.setDestinationLastFirstTel(userTel[2]);

		return orderConfirmationForm;
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
	public String orderComplete(@Validated OrderConfirmationForm form, BindingResult result, Model model) {
		// 入力値チェック
		if (result.hasErrors()) {
			return confirmOrder(model);
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
