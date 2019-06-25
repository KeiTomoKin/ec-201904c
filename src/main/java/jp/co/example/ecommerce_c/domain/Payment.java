package jp.co.example.ecommerce_c.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * クレジットカードによる支払いの情報を保持するクラス.
 *
 * @author takuya.aramaki
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Payment {
	/** 決済金額 */
    private Integer amount;
    /** カード番号 */
    private String cardNumber;
    /** 有効期限(年) */
    private Integer cardExpYear;
    /** 有効期限(月) */
    private Integer cardExpMonth;
    /** カード名義人 */
    private String cardName;
    /** セキュリティコード */
	private String cardCvv;

	@Override
	public String toString() {
		return "Payment [amount=" + amount + ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear
				+ ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public Integer getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
}
