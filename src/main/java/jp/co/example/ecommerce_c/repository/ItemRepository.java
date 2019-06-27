package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Item;

/**
 * Itemsテーブルを操作するリポジトリ.
 * 
 * @author keita.tomooka
 * @author takuya.aramaki
 *
 */
@Repository
public class ItemRepository {
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 指定したIDの商品を返します.
	 *
	 * @param id ID
	 * @return Itemオブジェクト。存在しないIDが指定された場合はnull。
	 */
	public Item load(Integer id) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			return template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 商品情報を全て取得する.
	 * 
	 * @return 商品情報のリスト
	 */
	public List<Item> findAll() {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 指定した名前を含む商品情報を取得する.
	 * 
	 * @param name 検索する名前
	 * @return 検索条件に一致した商品情報のリスト
	 */
	public List<Item> findByLikeName(String name) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE UPPER(name) LIKE UPPER(:name)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 人気順でソートされたアイテムリストを取得する.
	 * 同率の場合はID順に並びます.
	 * 
	 * @return ソートされたアイテムリスト
	 */
	public List<Item> findAllSortByPopularity() {
		String sql = "select i.id as id,i.name as name,description as description,price_m as price_m,price_l as price_l,image_path as image_path,deleted as deleted " + 
				"FROM items i LEFT OUTER JOIN order_items o ON o.item_id=i.id LEFT OUTER JOIN orders s ON o.order_id=s.id GROUP BY o.item_id,i.name,i.id,s.status " + 
				"ORDER BY CASE WHEN sum(quantity) is null THEN 0 ELSE sum( CASE WHEN s.status=0 THEN 0 ELSE quantity END ) END DESC,id;";
		return template.query(sql, ITEM_ROW_MAPPER);
		
	}
}
