package jp.co.example.ecommerce_c.domain;

/**
 * 発行したクーポンを表すドメイン.
 * 
 * @author keita.tomooka
 *
 */
public class IssuedTicket {
	/** ID */
	private Integer id;
	/** クーポンのID */
	private Integer CouponId;
	/** ユーザーのID */
	private Integer userId;
	/** クーポンコード */
	private String couponCode;
	/** クーポン */
	private Coupon coupon;

	@Override
	public String toString() {
		return "IssuedTicket [id=" + id + ", CouponId=" + CouponId + ", userId=" + userId + ", couponCode=" + couponCode
				+ "]";
	}

	public String getCouponCode() {
		return couponCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCouponId() {
		return CouponId;
	}

	public void setCouponId(Integer couponId) {
		CouponId = couponId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

}
