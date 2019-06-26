package jp.co.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Topping;
import jp.co.example.ecommerce_c.repository.ItemRepository;
import jp.co.example.ecommerce_c.repository.OrderItemRepository;
import jp.co.example.ecommerce_c.repository.OrderRepository;
import jp.co.example.ecommerce_c.repository.OrderToppingRepository;
import jp.co.example.ecommerce_c.repository.ToppingRepository;

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
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * ユーザIDで注文状態＝0のorderを検索.
	 * 
	 * @param userId ユーザID
	 * @return 検索結果のオーダー
	 */
	protected Order orderCheckByUserId(Integer userId) {
		try {
			Order order = orderRepository.findByUserId(userId);
			order.setUserId(userId);
			return order;
		} catch (EmptyResultDataAccessException e) {
			Order order = createNewOrder(userId);
			order.setUserId(userId);
			return order;
		}
	}

	/**
	 * ユーザIDに結びついたorderがない場合に、新しくorderを作る.
	 * 
	 * @param userId ユーザID
	 * @return 新しく作ったオーダー
	 */
	protected Order createNewOrder(Integer userId) {
		Order order = new Order();
		order.setUserId(userId);
		Integer orderId = orderRepository.insert(order);
		order.setId(orderId);
		return order;
	}

	/**
	 * ピザをショッピングカートに追加する.
	 * 
	 * @param pizza              追加する商品
	 * @param userId             ユーザID
	 * @param orderToppingIdList トッピングのIDのリスト
	 */
	public void addOrderItem(OrderItem pizza, Integer userId, List<Integer> orderToppingIdList) {
		Order order = orderCheckByUserId(userId);
		addOrderItem(pizza, order, orderToppingIdList);
	}

	/**
	 * ピザをショッピングカートに追加する.
	 *
	 * @param pizza              追加する商品
	 * @param order              追加される対象の注文
	 * @param orderToppingIdList トッピングのIDリスト
	 */
	protected void addOrderItem(OrderItem pizza, Order order, List<Integer> orderToppingIdList) {
		pizza.setOrderId(order.getId());
		orderItemRepository.insert(pizza);
		Item item = itemRepository.load(pizza.getItemId());
		pizza.setItem(item);

		addOrderToppingsToOrderItem(orderToppingIdList, pizza);

		if (order.getOrderItemList() == null) {
			order.setOrderItemList(new ArrayList<>());
		}
		order.getOrderItemList().add(pizza);
		order.setTotalPrice(order.getTotalPrice() + pizza.getSubTotal());
		orderRepository.update(order);
	}

	/**
	 * 商品にトッピングを追加する
	 *
	 * @param orderToppingIdList 追加するトッピングのリスト
	 * @param pizza              トッピングが追加される商品
	 */
	private void addOrderToppingsToOrderItem(List<Integer> orderToppingIdList, OrderItem pizza) {
		List<OrderTopping> orderToppingList = new ArrayList<>();
		if (orderToppingIdList != null) {
			for (Integer toppingId : orderToppingIdList) {
				OrderTopping topping = new OrderTopping();
				topping.setOrderItemId(pizza.getId());
				topping.setToppingId(toppingId);
				orderToppingRepository.insert(topping);
				orderToppingList.add(topping);
			}
		}
		pizza.setOrderToppingList(orderToppingList);
	}

	/**
	 * カート内の商品の一覧を表示する.
	 * 
	 * @param userId  ユーザID
	 * @param orderId オーダーID
	 * @return 注文内容(order)
	 */
	public Order showOrder(Integer userId, Integer orderId) {
		Order order;
		if (orderId == null && userId == -1) {
			order = createNewOrder(userId);
		} else {
			order = orderCheckByUserId(userId);
		}
		return setupOrder(order);
	}

	/**
	 * 注文に紐づいた商品やトッピングを取得する.
	 *
	 * @param order 表示する注文
	 * @return 追加済みの商品情報が入ったorder
	 */
	protected Order setupOrder(Order order) {
		Integer orderId = order.getId();
		List<OrderItem> orderItemList = orderItemRepository.findById(orderId);
		for (OrderItem pizza : orderItemList) {
			Item item = itemRepository.load(pizza.getItemId());
			pizza.setItem(item);
			List<OrderTopping> orderToppingList = orderToppingRepository.findById(pizza.getId());
			for (OrderTopping orderTopping : orderToppingList) {
				Topping topping = toppingRepository.load(orderTopping.getToppingId());
				orderTopping.setTopping(topping);
			}
			pizza.setOrderToppingList(orderToppingList);
		}
		order.setOrderItemList(orderItemList);
		return order;
	}

	/**
	 * カート内の商品を削除する.
	 * 
	 * @param orderItemId カートから削除するピザのID
	 * @param orderId     削除するピザを含んだオーダーのID
	 */
	public void deleteOrderItem(Integer orderItemId, Integer orderId) {
		Order order = orderRepository.findById(orderId);
		OrderItem orderItem = orderItemRepository.load(orderItemId);
		Item item = itemRepository.load(orderItem.getItemId());
		List<OrderTopping> orderToppingList = orderToppingRepository.findById(orderItemId);
		orderItem.setItem(item);
		if (orderToppingList == null) {
			orderToppingList = new ArrayList<>();
		}
		orderItem.setOrderToppingList(orderToppingList);
		order.setTotalPrice(order.getTotalPrice() - orderItem.getSubTotal());
		orderRepository.update(order);
		orderToppingRepository.deleteById(orderItemId);
		orderItemRepository.deleteById(orderItemId);
	}

}
