package jp.co.example.ecommerce_c.domain;

public class IssuedTicket {
	private Integer id;
	private Integer CouponId;
	private Integer userId;
	private String couponCode;
	public String getCouponCode() {
		return couponCode;
	}
	@Override
	public String toString() {
		return "IssuedTicket [id=" + id + ", CouponId=" + CouponId + ", userId=" + userId + ", couponCode=" + couponCode
				+ "]";
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
	
	
	
}
