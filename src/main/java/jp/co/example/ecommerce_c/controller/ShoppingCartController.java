package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.form.OrderItemForm;
import jp.co.example.ecommerce_c.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/add")
	public String addOrderItem(OrderItemForm form, @AuthenticationPrincipal LoginUser loginUser) {
		int userId;
		if(loginUser == null) {
			userId = -1;
		}else {
			userId = loginUser.getUser().getId();
		}
		Integer orderId = (Integer) session.getAttribute("orderId");
		System.out.println(orderId);
		shoppingCartService.insert(form, orderId, userId);
		orderId = shoppingCartService.orderCheckByUserId(userId).getId();
		session.setAttribute("orderId", orderId);
		session.setAttribute("userId", userId);
		return "redirect:/cart/";
	}

	@RequestMapping("/")
	public String showOrder() {
		Integer userId=(Integer) session.getAttribute("userId");
		Integer orderId = (Integer) session.getAttribute("orderId");
		Order order=shoppingCartService.showOrder(userId, orderId);
		orderId = shoppingCartService.orderCheckByUserId(userId).getId();
		session.setAttribute("order", order);
		session.setAttribute("orderId", orderId);
		return "cart_list";
	}

	@RequestMapping("/delete")
	public String deleteOrderItem(Integer orderItemId,Integer orderId) {
		shoppingCartService.deleteOrderItem(orderItemId,orderId);
		return "redirect:/cart/";
	}
}