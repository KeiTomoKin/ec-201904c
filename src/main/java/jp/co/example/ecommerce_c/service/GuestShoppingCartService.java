package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * ログインしていないユーザがショッピングカートの操作をするサービス.
 *
 * @author koichi.nagata
 *
 */
@Service
@Transactional
public class GuestShoppingCartService extends ShoppingCartService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrderItem(OrderItem orderItem, Integer userId, List<Integer> orderToppingIdList) {
		Order order = findOrCreateOrder(orderItem.getOrderId(), userId);
		addOrderItem(orderItem, order, orderToppingIdList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Order showOrder(Integer userId, Integer orderId) {
		Order order = findOrCreateOrder(orderId, userId);
		return setupOrder(order);
	}

	/**
	 * データベースから注文を探し、なければ新規に作って返します.
	 *
	 * @param orderId 探す注文のID
	 * @param userId  探す注文のユーザーID
	 * @return データベースから取得した、または新規に作った注文
	 */
	private Order findOrCreateOrder(Integer orderId, Integer userId) {
		if (orderId == null) {
			return createNewOrder(userId);
		} else {
			try {
				return orderRepository.findById(orderId);
			} catch (EmptyResultDataAccessException e) {
				return createNewOrder(userId);
			}
		}
	}
}
