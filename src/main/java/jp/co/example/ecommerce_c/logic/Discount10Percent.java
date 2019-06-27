package jp.co.example.ecommerce_c.logic;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.service.CouponService;

/**
 * 合計金額から10%引きを行うクラス.
 * @author keita.tomooka
 *
 */

public class Discount10Percent implements Coupons {
	/**
	 *合計金額から10%引きを行うメソッド.
	 */
	@Override
	public Order useCoupon(Order order) {
		System.out.println("1"+order);
		order.setTotalPrice((int)(order.getTotalPrice()*0.9));
		System.out.println("2"+order);
		System.out.println("3"+order);
		
		return order;
	}

	/**
	 *クーポンが利用できるか確認するためのメソッド.
	 */
	@Override
	public boolean checkCoupon(Order order) {

		return true;
	}

}
