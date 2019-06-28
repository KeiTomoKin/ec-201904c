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
	@Size(min = 0, max = 49, message = "文字数制限を超えています")
	@NotBlank(message = "名前を入力してください")
	@Pattern(regexp = "^[\\S]+$", message="半角スペースは登録できません")
	private String firstName;
	@Size(min = 0, max = 50, message = "文字数制限を超えています")
	@NotBlank(message = "名前を入力してください")
	@Pattern(regexp = "^[\\S]+$", message="半角スペースは登録できません")
	private String lastName;

	/** メールアドレス **/
	@Pattern(regexp = "^[0-9A-Za-z.@._.,]*$", message = "半角英数字のみです")
	@Size(min = 0, max = 100, message = "文字数制限を超えています")
	@Email(message = "メール形式で記入してください")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	/** パスワード **/
	@Pattern(regexp = ".{8,20}|", message = "文字数制約に反してます")
	@NotBlank(message="パスワードを入力してください")
	private String password;

	/** 確認パスワード **/
	@Pattern(regexp = ".{8,20}|", message = "文字数制約に反してます")
	@NotBlank(message="パスワードを再度入力してください")
	private String passwordAgain;

	/** 郵便番号 **/
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@Pattern(regexp = ".{7}|", message = "7文字で入力してください")
	@NotBlank(message = "郵便番号を入力してください")
	private String zipcode;

	/** 住所 **/
	@Size(min = 0, max = 200, message = "文字数制限を超えています")
	@NotBlank(message = "住所を入力してください")
	private String address;

	/** 電話番号 **/
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力してください")
	private String firstNumber;
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力してください")
	private String middleNumber;
	@Pattern(regexp = "^[0-9]*$", message = "半角数字のみです")
	@NotBlank(message = "電話番号を入力してください")
	private String lastNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(String firstNumber) {
		this.firstNumber = firstNumber;
	}

	public String getMiddleNumber() {
		return middleNumber;
	}

	public void setMiddleNumber(String middleNumber) {
		this.middleNumber = middleNumber;
	}

	public String getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(String latterNumber) {
		this.lastNumber = latterNumber;
	}

	@Override
	public String toString() {
		return "InsertUserForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", passwordAgain=" + passwordAgain + ", zipcode=" + zipcode + ", address=" + address
				+ ", firstNumber=" + firstNumber + ", middleNumber=" + middleNumber + ", lastNumber=" + lastNumber
				+ "]";
	}
}
