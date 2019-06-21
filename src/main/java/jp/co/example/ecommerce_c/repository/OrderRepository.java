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
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
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
	 * @param id sessionスコープ内のオーダーID
	 * @return オーダー
	 */
	public Order findById(Integer id) {
		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method"
				+ " FROM orders WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
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
		String sql = "SELECT id,user_id,status FROM orders WHERE user_id=:userId AND status=0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
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
