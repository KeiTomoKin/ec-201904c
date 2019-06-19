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
	 * ユーザ情報を登録します.
	 * 
	 * @param administrator ユーザ情報
	 */
	public void insert(User user) {
		userRepository.insert(user);
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
