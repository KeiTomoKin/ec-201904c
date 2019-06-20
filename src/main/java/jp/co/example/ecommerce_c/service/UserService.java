package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * ユーザー情報を操作するサービス.
 * 
 * @author takara.miyazaki
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * ユーザー情報の登録.
	 * 
	 * @param user 登録するユーザー情報
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスでユーザー情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findByEmail(String email) {
		return userRepository.findByMailAddress(email);
	}
	

	/**
	 * ログインする.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザ情報 存在しない場合はnullが返る
	 */
	public User login(String email, String password) {
		User user = userRepository.findByMailAddressAndPassward(email, password);
		if (user == null) {
			return null;
		}
		return user;
	}

}
