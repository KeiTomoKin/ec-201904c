package jp.co.example.ecommerce_c.logic;

import jp.co.example.ecommerce_c.domain.Order;

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
		order.setTotalPrice((int)(order.getTotalPrice()*0.9));
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
