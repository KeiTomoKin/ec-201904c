package jp.co.example.ecommerce_c.form;

/**
 * ログイン時に使用するフォーム.
 * ログイン処理はSpringSecurityで行っているため現在未使用
 * 
 * @author takara.miyazaki
 *
 */
public class LoginForm {

	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;

	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mailAddress) {
		this.email = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
