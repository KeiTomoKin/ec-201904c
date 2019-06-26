package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.OrderItem;

/**
 * 注文中商品の操作をするリポジトリ.
 * 
 * @author koichi.nagata
 *
 */
@Repository
public class OrderItemRepository {
	/** ピザのローマッパー */
	private static final RowMapper<OrderItem> ORDERITEM_ROW_MAPPER = (rs, i) -> {
		OrderItem pizza = new OrderItem();
		pizza.setId(rs.getInt("id"));
		pizza.setOrderId(rs.getInt("order_id"));
		pizza.setItemId(rs.getInt("item_id"));
		pizza.setQuantity(rs.getInt("quantity"));
		pizza.setSize(rs.getString("size").charAt(0));
		return pizza;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * リクエストパラメータを取得してピザの作成.
	 * 
	 * @param orderItem 新しいピザ
	 */
	public void insert(OrderItem orderItem) {
		String insertSql = "insert into order_items(item_id,order_id,quantity,size) values (:itemId,:orderId,:quantity,:size) RETURNING id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Integer id = template.queryForObject(insertSql, param, Integer.class);
		orderItem.setId(id);
	}

	/**
	 * 注文IDと紐づいているピザの検索.
	 * 
	 * @param orderId 注文ID
	 * @return 注文IDと紐づいているピザのリスト
	 */
	public List<OrderItem> findById(Integer orderId) {
		String sql = "SELECT id,item_id,order_id,quantity,size FROM order_items WHERE order_id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
//	try {
		List<OrderItem> orderItemList = template.query(sql, param, ORDERITEM_ROW_MAPPER);
//	}catch() {

//	}
		return orderItemList;
	}

	/**
	 * オーダーアイテムIDで、1枚のピザを呼び出す.
	 * 
	 * @param orderItemId オーダーするピザに自動採番されたID
	 * @return オーダー中のアイテム
	 */
	public OrderItem load(Integer orderItemId) {
		String sql = "SELECT id,item_id,order_id,quantity,size FROM order_items WHERE id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		OrderItem orderItem = template.queryForObject(sql, param, ORDERITEM_ROW_MAPPER);
		return orderItem;
	}

	/**
	 * ピザのオーダーを取り消す.
	 * 
	 * @param orderItemId 注文中のピザの個別ID
	 */
	public void deleteById(Integer orderItemId) {
		String deleteSql = "delete from order_items where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(deleteSql, param);
	}
}
