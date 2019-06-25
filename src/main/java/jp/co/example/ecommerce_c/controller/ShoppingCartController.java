package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.form.OrderItemForm;
import jp.co.example.ecommerce_c.service.GuestShoppingCartService;
import jp.co.example.ecommerce_c.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private GuestShoppingCartService guestShoppingCartService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/add")
	public String addOrderItem(OrderItemForm form, @AuthenticationPrincipal LoginUser loginUser) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		int userId;
		if(loginUser == null) {
			userId = -1;
			orderId=guestShoppingCartService.insert(form, orderId, userId);
		}else {
			userId = loginUser.getUser().getId();
			shoppingCartService.insert(form, orderId, userId);
			orderId = shoppingCartService.orderCheckByUserId(userId).getId();
		}
		session.setAttribute("orderId", orderId);
		return "redirect:/cart";
	}

	@RequestMapping("")
	public String showOrder(@AuthenticationPrincipal LoginUser loginUser) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		Order order = new Order();
		int userId;
		if(loginUser == null) {
			userId = -1;
			order=guestShoppingCartService.showOrder(userId, orderId);
		}else {
			userId = loginUser.getUser().getId();
			order=shoppingCartService.showOrder(userId, orderId);
		}
		orderId= order.getId();
		session.setAttribute("order", order);
		session.setAttribute("orderId", orderId);
		return "cart_list";
	}

	@RequestMapping("/delete")
	public String deleteOrderItem(Integer orderItemId,Integer orderId) {
		shoppingCartService.deleteOrderItem(orderItemId,orderId);
		return "redirect:/cart";
	}
}