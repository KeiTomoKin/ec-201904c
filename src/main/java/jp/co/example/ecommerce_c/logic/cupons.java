package jp.co.example.ecommerce_c.logic;

import jp.co.example.ecommerce_c.domain.Order;

/**
 * クーポンのインターフェース.
 * 
 * @author keita.tomooka
 *
 */
public interface cupons {

	/**
	 * クーポンを利用するメソッド.
	 * 
	 * @param order 注文情報
	 * @return クーポンを適用した注文情報
	 */
	public Order useCoupon(Order order);

	/**
	 * クーポンが利用できるか確認するためのメソッド.
	 * 
	 * @param order 注文情報
	 * @return クーポンの利用可否(boolean)
	 */
	public boolean checkCoupon(Order order);

}
