package jp.co.example.ecommerce_c.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * オーダー内容のドメイン.
 * 
 * @author koichi.nagata
 *
 */
public class Order {
	/** オーダーID */
	private Integer id;
	/** 注文者ID */
	private Integer userId;
	/** 注文の状態 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先名 */
	private String destinationName;
	/** 宛先メールアドレス */
	private String destinationEmail;
	/** 宛先住所 */
	private String destinationAddress;
	/** 宛先電話番号 */
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払方法 */
	private Integer paymentMethod;
	/** ユーザー */
	private User user;
	/** オーダーリスト */
	private List<OrderItem> orderItemList;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationAddress=" + destinationAddress + ", destinationTel=" + destinationTel
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}
	

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getUserId() {
		return userId;
	}

	public final void setUserId(Integer userId) {
		this.userId = userId;
	}

	public final Integer getStatus() {
		return status;
	}

	public final void setStatus(Integer status) {
		this.status = status;
	}

	public final Integer getTotalPrice() {
		return totalPrice;
	}

	public final void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public final Date getOrderDate() {
		return orderDate;
	}

	public final void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public final String getDestinationName() {
		return destinationName;
	}

	public final void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public final String getDestinationEmail() {
		return destinationEmail;
	}

	public final void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public final String getDestinationAddress() {
		return destinationAddress;
	}

	public final void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public final String getDestinationTel() {
		return destinationTel;
	}

	public final void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public final Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public final void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public final Integer getPaymentMethod() {
		return paymentMethod;
	}

	public final void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public final void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	/**
	 * 消費税のゲッター.
	 * 
	 * 未実装
	 * 
	 * @return 消費税
	 */
	public int getTax() {
		return (int)(getTotalPrice()*0.08);
	}

	/**
	 * 消費税別の合計金額のゲッター.
	 * 
	 * 未実装
	 * 
	 * @return 合計金額
	 */
	public int getCalcTotalPrice() {
		return getTotalPrice()+getTax();
	}
}
