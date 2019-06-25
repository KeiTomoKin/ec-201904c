package jp.co.example.ecommerce_c.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * クレジットカードによる決済結果をWebAPIから受け取るクラス.
 *
 * @author takuya.aramaki
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentResult {
	private String status;
	private String message;
	private String errorCode;
	
	public boolean isSuccess() {
		return "success".equals(status);
	}

	@Override
	public String toString() {
		return "PaymentResult [status=" + status + ", message=" + message + ", errorCode=" + errorCode + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
