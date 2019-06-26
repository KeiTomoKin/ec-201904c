package jp.co.example.ecommerce_c.form;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	/** 宛先郵便番号 **/
	@NotBlank(message = "郵便番号を入力して下さい")
	private String destinationZipcode;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	/** 宛先住所 **/
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	/** 宛先電話番号 **/
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationFirstTel;
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationMiddleFirstTel;
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationLastFirstTel;

	/** 注文日 **/
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NotNull(message = "配達日時を指定して下さい")
	private LocalDate deliveryDate;
<<<<<<< Updated upstream
	/** 配達時間 **/
	private String deliveryTime;
	/** 支払方法 **/
	private Integer paymentMethod;
=======

	/** 配達時間 **/
	private String deliveryTime;

	/** 支払方法 **/
	private Integer paymentMethod;

>>>>>>> Stashed changes
	/** クレジットカード情報 */
	@Valid
	private CreditCardForm creditCardForm;

	/** 送料、税込みの合計金額 */
	private Integer totalPrice;

	/** 送料 */
	private Integer cost;

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

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer status) {
		this.paymentMethod = status;
	}

	public CreditCardForm getCreditCardForm() {
		return creditCardForm;
	}

	public void setCreditCardForm(CreditCardForm creditCardForm) {
		this.creditCardForm = creditCardForm;
	}

	public final Integer getTotalPrice() {
		return totalPrice;
	}

	public final void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

	public final Integer getCost() {
		return cost;
	}

	public final void setCost(Integer cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "OrderConfirmationForm [destinationFirstName=" + destinationFirstName + ", destinationLastName="
				+ destinationLastName + ", destinationEmail=" + destinationEmail + ", destinationZipcode="
				+ destinationZipcode + ", destinationAddress=" + destinationAddress + ", destinationFirstTel="
				+ destinationFirstTel + ", destinationMiddleFirstTel=" + destinationMiddleFirstTel
				+ ", destinationLastFirstTel=" + destinationLastFirstTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", creditCardForm="
				+ creditCardForm + ", totalPrice=" + totalPrice + ", cost=" + cost + "]";
	}

}
