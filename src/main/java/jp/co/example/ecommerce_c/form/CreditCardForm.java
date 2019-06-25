package jp.co.example.ecommerce_c.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * クレジットカード情報を受け取るフォームクラス.
 *
 * @author takuya.aramaki
 */
public class CreditCardForm {
	/** カード番号 */
	@NotBlank(message = "カード番号を入力してください")
	@Pattern(regexp = "(\\d{14,16})?", message = "カード番号の形式が不正です")
    private String cardNumber;
    /** 有効期限(年) */
	@NotNull(message = "有効期限の年を選択してください")
    private Integer cardExpYear;
    /** 有効期限(月) */
	@NotNull(message = "有効期限の月を選択してください")
    private Integer cardExpMonth;
    /** カード名義 */
	@NotBlank(message = "カード名義人を入力してください")
    private String cardName;
    /** セキュリティコード */
	@NotBlank(message = "セキュリティコードを入力してください")
	private String cardCvv;

	@Override
	public String toString() {
		return "CreditCardForm [cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth="
				+ cardExpMonth + ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
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
