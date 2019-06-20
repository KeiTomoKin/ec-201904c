package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Order;

/**
 * Orderドメインを操作するリポジトリー.
 * 
 * @author koichi.nagata
 *
 */
@Repository
public class OrderRepository {

	/** Orderから生成するローマッパー */
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("userId"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("size"));
		return order;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * userIdを取得して新たなオーダーの作成.
	 * 
	 * @param order 新しいオーダー
	 */
	public void insert(Order order) {
		String insertSql = "insert into orders(user_id,status, total_price) values (:userId,0,0)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(insertSql, param);
	}

	/**
	 * orderIdからユーザのオーダーを検索.
	 * 
	 * @param orderId sessionスコープ内のオーダーID
	 * @return status=0のオーダー
	 */
	public Order findById(Integer orderId) {
		String sql = "SELECT user_id,status, total_price FROM orders WHERE id=:orderId AND status=0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
//		try {
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
//		}catch() {

//		}
		return order;
	}

	/**
	 * ユーザの注文履歴からオーダーを検索.
	 * 
	 * @param userId ユーザID
	 * @return status=0のオーダー
	 */
	public Order findByUserId(Integer userId) {
		String sql = "SELECT id FROM orders WHERE user_id=:userId AND status=0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}

	/**
	 * オーダーの取り消しをする.
	 * 
	 * @param orderId オーダーID
	 */
	public void deleteById(Integer orderId) {
		String deleteSql = "delete from orders where id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		template.update(deleteSql, param);
	}
}
