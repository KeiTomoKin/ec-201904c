package jp.co.example.ecommerce_c.form;

import javax.validation.constraints.NotBlank;

/**
 * ユーザー登録用フォームです
 * 
 * @author kazuya.makida
 *
 */
public class InsertUserForm {
	/** 名前 **/
	@NotBlank(message="名前を入力してください")
	private String name;
	/** メールアドレス **/
	@NotBlank(message="メールアドレスを入力してください")
	private String email;
	/** パスワード **/
	@NotBlank(message="パスワードを入力してください")
	private String password;
	/** 確認パスワード **/
	@NotBlank(message="確認用パスワードを入力してください")
	private String passwordAgain;
	/** 郵便番号 **/
	@NotBlank(message="郵便番号を入力してください")
	private String zipcode;
	/** 住所 **/
	@NotBlank(message="住所を入力してください")
	private String address;
	/** 電話番号 **/
	@NotBlank(message="電話番号を入力してください")
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
