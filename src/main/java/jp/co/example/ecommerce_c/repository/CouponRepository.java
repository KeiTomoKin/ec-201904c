package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Coupon;

/**
 * Couponsテーブルを操作するリポジトリ.
 * 
 * @author keita.tomooka
 *
 */
@Repository
public class CouponRepository {
	private static final RowMapper<Coupon> COUPON_ROW_MAPPER = (rs, i) -> {
		Coupon coupon = new Coupon();
		coupon.setId(rs.getInt("id"));
		coupon.setName(rs.getString("name"));
		coupon.setDescription(rs.getString("description"));
		coupon.setClassName(rs.getString("class_name"));
		coupon.setDeadline(rs.getDate("deadline"));
		return coupon;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * idからクーポンを取得する.
	 * 
	 * @param couponId クーポンのid
	 * @return クーポン
	 */
	public Coupon findCouponByCouponId(Integer couponId) {

		String sql = "SELECT id,name,description,class_name,deadline FROM coupons WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", couponId);
		try {
			return template.queryForObject(sql, param, COUPON_ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
