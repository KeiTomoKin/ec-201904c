package jp.co.example.ecommerce_c.form;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 注文内容確認画面の登録フォームです.
 * 
 * @author kazuya.makida
 *
 */
public class OrderConfirmationForm {
	/** 宛先名 **/
	@Size(min = 0, max = 49, message = "文字数制限を超えています")
	@NotBlank(message = "お名前を入力して下さい")
	private String destinationFirstName;
	@Size(min = 0, max = 50, message = "文字数制限を超えています")
	@NotBlank(message = "お名前を入力して下さい")
	private String destinationLastName;

	/** 宛先メールアドレス **/
	@Pattern(regexp = "^[0-9A-Za-z.@._.,]*$", message = "半角英数字のみです")
	@Size(min = 0, max = 100, message = "文字数制限を超えています")
	@Email(message = "メール形式で記入してください")
	@NotBlank(message = "メールアドレスを入力して下さい")
	private String destinationEmail;
	
	/** 宛先郵便番号 **/
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@Pattern(regexp = ".{7}|", message = "7文字で入力してください")
	@NotBlank(message = "郵便番号を入力して下さい")
	private String destinationZipcode;
	
	/** 宛先住所 **/
	@Size(min = 0, max = 200, message = "文字数制限を超えています")
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
	
	/** 宛先電話番号 **/
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationFirstTel;
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationMiddleFirstTel;
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationLastFirstTel;

	/** 注文日 **/
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NotNull(message = "配達日時を指定して下さい")
	private LocalDate deliveryDate;
	
	/** 配達時間 **/
	private String deliveryTime;
	
	/** 支払方法 **/
	private Integer paymentMethod;
	
	/** クレジットカード情報 */
	@Valid
	private CreditCardForm creditCardForm;

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
}
