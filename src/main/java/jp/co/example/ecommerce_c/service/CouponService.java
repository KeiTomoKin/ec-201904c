package jp.co.example.ecommerce_c.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.controller.GlobalExceptionHandler;
import jp.co.example.ecommerce_c.domain.Coupon;
import jp.co.example.ecommerce_c.domain.IssuedTicket;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.logic.Coupons;
import jp.co.example.ecommerce_c.repository.CouponRepository;
import jp.co.example.ecommerce_c.repository.IssuedTicketRepository;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * クーポン関連機能の業務処理を行うサービスクラス.
 * 
 * @author keita.tomooka
 *
 */
@Service
@Transactional
public class CouponService {
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IssuedTicketRepository issuedTicketRepository;
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * idからクーポンを取得する.
	 * 
	 * @param couponId クーポンid
	 * @return クーポン情報
	 */
	public Coupon findCouponByCouponId(Integer couponId) {
		return couponRepository.findCouponByCouponId(couponId);
	}

	/**
	 * ユーザIDとクーポンコードから発見済みのクーポンを取得する.
	 * 
	 * @param userId     ユーザID
	 * @param couponCode クーポンコード
	 * @return 発券済みのクーポン情報
	 */
	public IssuedTicket findCouponByUserIdAndCouponCode(Integer userId, String couponCode) {
		return issuedTicketRepository.findCouponByUserIdAndCouponCode(userId, couponCode);
	}

	/**
	 * クーポンが利用できるかどうか確認する.
	 * @param order 注文情報
	 * @param issuedTicket 発券済みのクーポン情報 
	 * @return 利用可否
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public boolean checkCoupon(Order order, IssuedTicket issuedTicket)
			 {
		Coupons coupons = null;
		try {
			Class<?> myClass = Class.forName(issuedTicket.getCoupon().getClassName());
			coupons = (Coupons) myClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return coupons.checkCoupon(order);
	}

	/**クーポンを利用する.
	 * @param order 注文情報
	 * @param issuedTicket 発券済みのクーポン情報
	 * @return クーポンを反映したオーダー情報
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Order useCoupon(Order order, IssuedTicket issuedTicket) {
		Coupons coupons = null;
		try {
			Class<?> myClass = Class.forName(issuedTicket.getCoupon().getClassName());
			coupons = (Coupons) myClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
//		coupons.checkCoupon(order);
		return coupons.useCoupon(order);
	}
	
	public void update(Order order) {
		orderRepository.update(order);
	}
}
