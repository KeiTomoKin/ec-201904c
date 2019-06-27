package jp.co.example.ecommerce_c.logic;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;

public class Disount100yenPer1P implements Coupons {

	@Override
	public Order useCoupon(Order order) {
	    int pizacount=0;
	    for(OrderItem orderItem:order.getOrderItemList()) {
	    	pizacount+=orderItem.getQuantity();
	    }
		order.setTotalPrice((int)(order.getTotalPrice())-pizacount*100);
		return order;
	}

	@Override
	public boolean checkCoupon(Order order) {
		return true;
	}

}
