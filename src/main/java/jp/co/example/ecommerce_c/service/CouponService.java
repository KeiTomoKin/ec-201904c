package jp.co.example.ecommerce_c.service;

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

	/**
	 * idからクーポンを取得する.
	 * 
	 * @param couponId クーポンid
	 * @return クーポン情報
	 */
	public Coupon findCouponByCouponId(Integer couponId) {
		return couponRepository.findCouponByCouponId(couponId);
	}

	public IssuedTicket findCouponByUserIdAndCouponCode(Integer userId, String couponCode) {
		return issuedTicketRepository.findCouponByUserIdAndCouponCode(userId, couponCode);
	}

	public boolean checkCoupon(Order order, IssuedTicket issuedTicket)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Coupons coupons = null;
		try {
			Class<?> myClass = Class.forName(issuedTicket.getCoupon().getClassName());
			coupons = (Coupons) myClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			if (e instanceof InstantiationException) {
				throw new InstantiationException();
			} else if (e instanceof IllegalAccessException) {
				throw new IllegalAccessException();
			} else if (e instanceof ClassNotFoundException) {
				throw new ClassNotFoundException();
			}
		}

		return coupons.checkCoupon(order);
	}

	public Order useCoupon(Order order, IssuedTicket issuedTicket)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Coupons coupons = null;
		try {
			Class<?> myClass = Class.forName(issuedTicket.getCoupon().getClassName());
			coupons = (Coupons) myClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			if (e instanceof InstantiationException) {
				throw new InstantiationException();
			} else if (e instanceof IllegalAccessException) {
				throw new IllegalAccessException();
			} else if (e instanceof ClassNotFoundException) {
				throw new ClassNotFoundException();
			}
		}
//		coupons.checkCoupon(order);
		return coupons.useCoupon(order);
	}
}
