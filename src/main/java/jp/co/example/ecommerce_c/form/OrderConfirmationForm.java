package jp.co.example.ecommerce_c.form;

import javax.validation.constraints.NotBlank;

/**
 * 注文内容確認画面の登録フォームです.
 * 
 * @author kazuya.makida
 *
 */
public class OrderConfirmationForm {
	/** 宛先名 **/
	@NotBlank(message = "お名前を入力して下さい")
	private String destinationFirstName;
	@NotBlank(message = "お名前を入力して下さい")
	private String destinationLastName;

	/** 宛先メールアドレス **/
	@NotBlank(message = "メールアドレスを入力して下さい")
	private String destinationEmail;
	/** 宛先郵便番号 **/
	@NotBlank(message = "郵便番号を入力して下さい")
	private String destinationZipcode;
	/** 宛先住所 **/
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
	/** 宛先電話番号 **/
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationFirstTel;
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationMiddleFirstTel;
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationLastFirstTel;
	
	/** 注文日 **/
	@NotBlank(message = "配達日時を入力して下さい")
	private String orderDate;
	/** 配達時間 **/
	private String deliveryTime;
	/** 支払方法 **/
	private String paymentMethod;

	public String getDestinationFirstName() {
		return destinationFirstName;
	}

	public void setDestinationFirstName(String destinationFirstName) {
		this.destinationFirstName = destinationFirstName;
	}

	public String getDestinationLastName() {
		return destinationLastName;
	}

	public void setDestinationLastName(String destinationLastName) {
		this.destinationLastName = destinationLastName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationFirstTel() {
		return destinationFirstTel;
	}

	public void setDestinationFirstTel(String destinationTel) {
		this.destinationFirstTel = destinationTel;
	}

	public String getDestinationMiddleFirstTel() {
		return destinationMiddleFirstTel;
	}

	public void setDestinationMiddleFirstTel(String destinationMiddleFirstTel) {
		this.destinationMiddleFirstTel = destinationMiddleFirstTel;
	}

	public String getDestinationLastFirstTel() {
		return destinationLastFirstTel;
	}

	public void setDestinationLastFirstTel(String destinationLastFirstTel) {
		this.destinationLastFirstTel = destinationLastFirstTel;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String status) {
		this.paymentMethod = status;
	}
}
