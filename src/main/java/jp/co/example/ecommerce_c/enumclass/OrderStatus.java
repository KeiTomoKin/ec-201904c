package jp.co.example.ecommerce_c.enumclass;

import java.util.Arrays;

public enum OrderStatus {
	NOT_ORDERED(0, "未注文"),
	NOT_PAID(1, "未入金"),
	PAID(2, "入金済"),
	DELIVERED(3, "発送済"),
	CANCELED(9, "キャンセル");

	/** 
	 * DBに登録する値.
	 * 0 = 注文前
	 * 1 = 未入金
	 * 2 = 入金済
	 * 3 = 発送済
	 * 9 = キャンセル
	 */
	private final int code;
	/** 状態を説明する文字列 */
	private final String description;

	private OrderStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	/**
	 * コードに対応するOrderStatusを返します.
	 *
	 * @param code DBに登録されるコード
	 * @return 対応するOrderStatus
	 * @throws RuntimeException codeに対応するものが見つからなかった場合
	 */
	public static OrderStatus getByCode(int code) {
		return Arrays.stream(OrderStatus.values())
				.filter(status -> status.code == code)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("存在しないステータスコードです"));
	}
	
	public boolean isOrdered() {
		return this != NOT_ORDERED;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
