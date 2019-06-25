package jp.co.example.ecommerce_c.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
import jp.co.example.ecommerce_c.service.UserService;

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
	private UserService userService;

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
		order.setUser(userService.findByUserId(order.getUserId()));
//		User user = userService.findByUserId(order.getUserId());

		model.addAttribute("order", order);
//		model.addAttribute("user", user);

		return "order_confirm";
	}

	@RequestMapping("/orderComplete")
	public String orderComplete(@Validated OrderConfirmationForm form, BindingResult result, Model model) {
		// 入力値チェック
		if (result.hasErrors()) {
			return confirmOrder(model);
		}

		Order order = (Order) session.getAttribute("order");

		// フォームから注文情報をコピー
		BeanUtils.copyProperties(form, order);

		// コピーできなかった各情報の作成
		Date orderDate = Date.valueOf(LocalDate.now());
		order.setOrderDate(orderDate);

		order.setDestinationName(form.getDestinationFirstName() + " " + form.getDestinationLastName());
		order.setDestinationTel(form.getDestinationFirstTel() + "-" + form.getDestinationMiddleFirstTel() + "-"
				+ form.getDestinationLastFirstTel());

		// 配達日時の作成

		LocalDate deliveryDate = form.getDeliveryDate();
		LocalTime deliveryTime = LocalTime.of(Integer.parseInt(form.getDeliveryTime()), 0);
		LocalDateTime deliveryDateTime = LocalDateTime.of(deliveryDate, deliveryTime);

		order.setDeliveryTime(Timestamp.valueOf(deliveryDateTime));

		order.setStutasByPaymentMethod();

		order.setUser(userService.findByUserId(order.getUserId()));

		System.out.println(order.toString());

		// データベースへアップロード
		orderConfirmationService.orderComplete(order);

		return "order_finished";
	}
}
