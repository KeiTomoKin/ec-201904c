package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.User;

/**
 * usersテーブルを操作するレポジトリ.
 * 
 * @author takara.miyazaki
 *
 */
@Repository
public class UserRepository {

	/**
	 * Userオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name,email,password,zipcode,address,telephone)"
				+ "values(:name,:email,:password,:zipcode,:address,:telephone);";
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスでユーザー情報を取得.
	 * 
	 * @param email メールアドレス
	 * @return 検索結果
	 */
	public User findByMailAddress(String email) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		
		if(userList.size()==0) {
			return null;
		}else {
			return userList.get(0);
		}
	}

	/**
	 * メールアドレスとパスワードからユーザー情報を取得.
	 * 
	 * @param email    メールアドレス
	 * @param passward パスワード
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public User findByMailAddressAndPassward(String email, String password) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where email=:email and password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * ユーザーIDからユーザー情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @return 検索結果
	 */
	public User findByUserId(Integer userId) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", userId);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * ユーザ情報を更新する.
	 * 
	 * @param user 更新用のユーザデータ
	 */
	public void update(User user) {
		String updateSql = "UPDATE users SET name=:name, email=:email,"
						+ "zipcode=:zipcode, address=:address, telephone=:telephone where id=:id;";
//		String updateSql = "UPDATE users SET name=:name, email=:email, password=:password, "
//				+ "zipcode=:zipcode, address=:address, telephone=:telephone where id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(updateSql, param);
	}
}
