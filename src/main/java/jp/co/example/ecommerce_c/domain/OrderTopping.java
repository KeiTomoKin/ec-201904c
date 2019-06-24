package jp.co.example.ecommerce_c.domain;

/**
 * 注文トッピングのドメイン
 * 
 * @author kazuya.makida
 *
 */
public class OrderTopping {
	/** Mサイズのトッピング料金 */
	public static final int PRICE_M = 200;
	/** Lサイズのトッピング料金 */
	public static final int PRICE_L = 300;

	/** ID */
	private Integer id;
	/** トッピング */
	private Integer toppingId;
	/** 注文商品のID */
	private Integer orderItemId;
	/** トッピング */
	private Topping topping;
	

	@Override
	public String toString() {
		return "User [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", topping=" + topping + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

}
