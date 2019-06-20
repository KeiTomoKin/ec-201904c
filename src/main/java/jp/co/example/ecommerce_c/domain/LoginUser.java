package jp.co.example.ecommerce_c.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ユーザのログイン情報を格納するドメイン.
 * 
 * @author takara.miyazaki
 *
 */
public class LoginUser extends User{
	

	private static final long serialVersionUID = 1L;
	/** ユーザ情報 */
	private final jp.co.example.ecommerce_c.domain.User user;
	
	public LoginUser(jp.co.example.ecommerce_c.domain.User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}

	public jp.co.example.ecommerce_c.domain.User getUser() {
		return user;
	}

}
