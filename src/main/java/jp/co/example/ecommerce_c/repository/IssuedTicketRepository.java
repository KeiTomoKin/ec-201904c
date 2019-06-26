package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.IssuedTicket;

/**
 * 発行したクーポンのリポジトリ.
 * 
 * @author keita.tomooka
 *
 */
@Repository
public class IssuedTicketRepository {
	private static final RowMapper<IssuedTicket> ISSUED_TICKET_ROW_MAPPER = (rs, i) -> {
		IssuedTicket coupon = new IssuedTicket();
		coupon.setId(rs.getInt("id"));
		coupon.setCouponId(rs.getInt("coupon_id"));
		coupon.setUserId(rs.getInt("user_id"));
		coupon.setCouponCode(rs.getString("coupon_code"));
		return coupon;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザーIDとクーポンコードからクーポンを取得する.
	 * 
	 * @param userId ユーザーID
	 * @param couponCode クーポンコード
	 * @return 発行済みのクーポン
	 */
	
	public IssuedTicket FindCouponByUserIdAndCouponCode(Integer userId, String couponCode) {

		String sql = "SELECT id,coupon_id,user_id,coupon_code FROM issued_tickets WHERE user_id=:userId AND coupon_code=:couponCode";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("couponCode",
				couponCode);
		try {
			return template.queryForObject(sql, param, ISSUED_TICKET_ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
