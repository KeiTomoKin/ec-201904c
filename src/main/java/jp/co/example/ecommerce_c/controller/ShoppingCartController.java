package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String addOrderItem(OrderItemForm form, Integer userId) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		System.out.println(orderId);
		shoppingCartService.insert(form, orderId, userId);
		orderId = shoppingCartService.orderCheckByUserId(userId).getId();
		session.setAttribute("orderId", orderId);
		return showOrder(userId);
	}

	@RequestMapping("/")
	public String showOrder(Integer userId) {
		Integer orderId = (Integer) session.getAttribute("orderId");
		System.out.println("show"+orderId);
		System.out.println("showの"+userId);
		Order order=shoppingCartService.showOrder(userId, orderId);
		System.out.println("オーダー"+order.getOrderItemList());
		orderId = shoppingCartService.orderCheckByUserId(userId).getId();
		session.setAttribute("order", order);
		session.setAttribute("orderId", orderId);
		return "cart_list";
	}

	@RequestMapping("/delete")
	public String deleteOrderItem(Integer orderItemId) {
		shoppingCartService.deleteOrderItem(orderItemId);
		return "redirect:/cart/";
	}
}