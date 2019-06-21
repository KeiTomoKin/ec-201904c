package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.form.OrderItemForm;
import jp.co.example.ecommerce_c.repository.ItemRepository;
import jp.co.example.ecommerce_c.repository.OrderItemRepository;
import jp.co.example.ecommerce_c.repository.OrderRepository;
import jp.co.example.ecommerce_c.repository.OrderToppingRepository;

/**
 * ショッピングカートの操作をするサービス.
 * 
 * @author koichi.nagata
 *
 */
@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * ユーザIDで注文状態＝0のorderを検索.
	 * 
	 * @param userId ユーザID
	 * @return 検索結果のオーダーID
	 */
	public Integer orderCheckByUserId(Integer userId) {
		Order order = orderRepository.findByUserId(userId);
		return order.getId();
	}

	/**
	 * ユーザIDに結びついたorderがない場合に、新しくorderを作る.
	 * 
	 * @param userId ユーザID
	 * @return 新しく作ったオーダーのID
	 */
	public int createNewOrder(int userId) {
		Order order = new Order();
		order.setUserId(userId);
		orderRepository.insert(order);
		order = orderRepository.findByUserId(userId);
		return order.getId();
	}

	/**
	 * ピザをショッピングカートに追加する.
	 * 
	 * @param form    フォーム
	 * @param orderId オーダーID
	 * @param userId  ユーザID
	 */
	public void insert(OrderItemForm form, Integer orderId, Integer userId) {
		if (orderId == null) {
			orderId = createNewOrder(userId);
		}
		OrderItem pizza = new OrderItem();
		pizza.setItemId(Integer.parseInt(form.getItemId()));
		pizza.setQuantity(Integer.parseInt(form.getQuantity()));
		pizza.setSize(form.getSize().charAt(0));

		for (Integer toppingId : form.getOrderToppingList()) {
			OrderTopping topping = new OrderTopping();
			topping.setOrderItemId(pizza.getId());
			topping.setToppingId(toppingId);
			orderToppingRepository.insert(topping);
			orderItemRepository.insert(pizza);
		}
	}

	/**
	 * カート内の商品の一覧を表示する.
	 * 
	 * @param userId  ユーザID
	 * @param orderId オーダーID
	 * @return 注文内容(order)
	 */
	public Order showOrder(Integer userId, Integer orderId) {
		if (orderId == null) {
			if (orderRepository.findByUserId(userId) == null || userId == -1) {
				orderId = createNewOrder(userId);
			}
		}
		Order order = orderRepository.findById(orderId);
		List<OrderItem> orderItemList = orderItemRepository.findById(orderId);
		for (OrderItem pizza : orderItemList) {
			Item item = itemRepository.load(pizza.getId());
			pizza.setItem(item);
			pizza.setOrderToppingList(orderToppingRepository.findById(pizza.getItemId()));
		}
		order.setOrderItemList(orderItemList);
		return order;
	}

	/**
	 * カート内の商品を削除する.
	 * 
	 * @param orderItemId 注文中のピザのID
	 */
	public void deleteOrderItem(Integer orderItemId) {
		orderToppingRepository.deleteById(orderItemId);
		orderItemRepository.deleteById(orderItemId);
	}
}
