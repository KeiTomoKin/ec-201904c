package jp.co.example.ecommerce_c.domain;

import java.util.List;

/**
 * 注文商品を表すクラス.
 *
 * @author takuya.aramaki
 */
public class OrderItem {
	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングのリスト */
	private List<OrderTopping> orderToppingList;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	/**
	 * 小計を返します. (ピザ1枚の価格 + トッピング1つの価格 * トッピングの数) * ピザの枚数 です。
	 *
	 * @return 小計
	 */
	public int getSubTotal() {
		int toppingPrice;
		switch (size) {
		case 'M':
			toppingPrice = OrderTopping.PRICE_M * orderToppingList.size();
			return (item.getPriceM() + toppingPrice) * quantity;
		case 'L':
			toppingPrice = OrderTopping.PRICE_L * orderToppingList.size();
			return (item.getPriceL() + toppingPrice) * quantity;
		default:
			throw new RuntimeException("サイズが間違っています: " + size);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
}
