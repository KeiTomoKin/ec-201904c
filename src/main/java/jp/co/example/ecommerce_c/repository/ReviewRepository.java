package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Review;

/**
 * レビューコメントのリポジトリー.
 * 
 * @author koichi.nagata
 *
 */
@Repository
public class ReviewRepository {
	/** コメントの要素を生成するローマッパー */
	public static final RowMapper<Review> REVIEW_ROW_MAPPER = (rs, i) -> {
		Review review = new Review();
		review.setId(rs.getInt("id"));
		review.setName(rs.getString("name"));
		review.setContent(rs.getString("content"));
		review.setEvaluation(rs.getInt("evaluation"));
		return review;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品IDからレビュー一覧を検索する.
	 * 
	 * @param articleId 記事ID
	 * @return 結びついたコメントリスト
	 */
	public List<Review> findByItemId(Integer itemId) {
		String sql = "select id,name,content,evaluation from reviews where item_id = :id order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", itemId);
		List<Review> reviewList = template.query(sql, param, REVIEW_ROW_MAPPER);
		return reviewList;
	}

	/**
	 * レビューを投稿する.
	 * 
	 * @param review 投稿フォームに入力した内容
	 */
	public void insert(Review review) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(review);
		String sql = "insert into reviews (name,content,item_id,evaluation) VALUES (:name,:content,:itemId,:evaluation)";
		template.update(sql, param);
	}

	/**
	 * レビューの削除(紐づいている記事が削除されるとき).
	 * 
	 * @param itemId 商品ID
	 */
	public void deleteByItemId(Integer itemId) {
		String sql = "delete from reviews where item_id = :itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		template.update(sql, param);
	}

	/**
	 * レビュー評価の平均値を計算する.
	 * 
	 * @param itemId 商品ID
	 * @return evaluationの平均値
	 */
	public double averageByItemId(Integer itemId) {
		String sql = "SELECT avg(evaluation) FROM reviews WHERE item_id=:itemId GROUP BY item_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		return template.queryForObject(sql, param,double.class);
	}
}
