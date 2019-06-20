package jp.co.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * ログイン後のユーザにユーザ権限を付与するサービスクラス.
 * 
 * @author takara.miyazaki
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * DBから検索をし、ログイン情報を構成して返す.
	 * 
	 * @param email メールアドレス
	 * @return ログインしたユーザー情報
	 * @throws UsernameNotFoundException DBからメアドが見つからなかったときエラー
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		User user = userRepository.findByMailAddress(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません.");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); //ユーザー権限付与
		return new LoginUser(user, authorityList);
	}
	

}
