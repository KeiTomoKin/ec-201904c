package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Topping;

/**
 * トッピングのレポジトリクラス.
 *
 * @author takuya.aramaki
 */
@Repository
public class ToppingRepository {
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 指定したIDのトッピングを取得します.
	 *
	 * @param id ID
	 * @return Toppingオブジェクト
	 * @throws EmptyResultDataAccessException 指定したIDのトッピングが存在しない場合
	 */
	public Topping load(Integer id) {
		String sql = "SELECT id,name,price_m,price_l FROM toppings WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, TOPPING_ROW_MAPPER);
	}

	/**
	 * トッピングを全て取得します.
	 *
	 * @return 全てのトッピングのリスト
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id,name,price_m,price_l FROM toppings;";
		return template.query(sql, TOPPING_ROW_MAPPER);
	}
}
