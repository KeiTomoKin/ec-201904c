package jp.co.example.ecommerce_c.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザー登録用フォームです
 * 
 * @author kazuya.makida
 *
 */
public class InsertUserForm {

	/** 名前 **/
	@Size(min = 0, max = 100, message = "文字数制限を超えています")
	@NotBlank(message = "名前を入力してください")
	private String name;

	/** メールアドレス **/
	@Pattern(regexp = "^[0-9A-Za-z.@]*$", message = "半角英数字のみです")
	@Size(min = 0, max = 100, message = "文字数制限を超えています")
	@Email(message = "メール形式で記入してください")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	/** パスワード **/
	@Size(min = 8, max = 20, message = "文字数制限に反してます")
	private String password;

	/** 確認パスワード **/
	@Size(min = 8, max = 20, message = "文字数制限に反してます")
	private String passwordAgain;

	/** 郵便番号 **/
	@Pattern(regexp = "^([0-9]{7}|)$", message = "半角数字のみです")
	@Size(min = 0, max = 7, message = "文字数制限を超えています")
	@NotBlank(message = "郵便番号を入力してください")
	private String zipcode;

	/** 住所 **/
	@Size(min = 0, max = 200, message = "文字数制限を超えています")
	@NotBlank(message = "住所を入力してください")
	private String address;

	/** 電話番号 **/
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力してください")
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
