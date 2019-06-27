package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
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
	public String addOrderItem(@Validated OrderItemForm form, BindingResult result, @AuthenticationPrincipal LoginUser loginUser) {
		if (result.hasErrors()) {
			return "forward:/item/detail";
		}

		Integer orderId = (Integer) session.getAttribute("orderId");

		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);
		orderItem.setSize(form.getSize().charAt(0));
		orderItem.setOrderId(orderId);

		if (loginUser == null) {
			int userId = -1;
			guestShoppingCartService.addOrderItem(orderItem, userId, form.getOrderToppingList());
		} else {
			int userId = loginUser.getUser().getId();
			shoppingCartService.addOrderItem(orderItem, userId, form.getOrderToppingList());
		}
		session.setAttribute("orderId", orderItem.getOrderId());
		return "redirect:/cart";
	}

	@RequestMapping("")
	public String showOrder(@AuthenticationPrincipal LoginUser loginUser) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		Order order;
		int userId;
		if (loginUser == null) {
			userId = -1;
			order = guestShoppingCartService.showOrder(userId, orderId);
		} else {
			userId = loginUser.getUser().getId();
			order = shoppingCartService.showOrder(userId, orderId);
		}
		orderId = order.getId();
		session.setAttribute("order", order);
		session.setAttribute("orderId", orderId);
		return "cart_list";
	}

	@RequestMapping("/delete")
	public String deleteOrderItem(Integer orderItemId, Integer orderId) {
		shoppingCartService.deleteOrderItem(orderItemId, orderId);
		return "redirect:/cart";
	}

}