package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.OrderTopping;

/**
 * トッピングドメイン操作をするリポジトリ.
 * 
 * @author koichi.nagata
 *
 */
@Repository
public class OrderToppingRepository {
	/** トッピングローマッパー */
	private static final RowMapper<OrderTopping> ORDERTOPPING_ROW_MAPPER = (rs, i) -> {
		OrderTopping topping = new OrderTopping();
		topping.setId(rs.getInt("id"));
		topping.setToppingId(rs.getInt("topping_id"));
		topping.setOrderItemId(rs.getInt("order_item_id"));
		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ピザに乗せるトッピングのカラムを作る.
	 * 
	 * @param orderTopping 乗せる材料
	 */
	public void insert(OrderTopping orderTopping) {
		String insertSql = "insert into order_toppings(topping_id,order_item_id) values (:toppingId,:orderItemId)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		template.update(insertSql, param);
	}

	/**
	 * 特定の(注文中)のピザに載っているトッピングリスト.
	 * 
	 * @param orderItemId 注文中のピザID
	 * @return トッピングのリスト
	 */
	public List<OrderTopping> findById(Integer orderItemId) {
		String sql = "SELECT id,topping_id,order_item_id FROM order_toppings WHERE order_item_id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
//	try {
		List<OrderTopping> orderToppingList = template.query(sql, param, ORDERTOPPING_ROW_MAPPER);
//	}catch() {

//	}
		return orderToppingList;
	}

	/**
	 * 注文を削除するピザに載っているトッピングのリストを削除する.
	 * 
	 * @param orderItemId 削除するピザのID
	 */
	public void deleteById(Integer orderItemId) {
		String deleteSql = "delete from order_toppings where id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(deleteSql, param);
	}
}
