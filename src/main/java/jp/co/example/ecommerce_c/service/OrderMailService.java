package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.ThymeleafText;
import jp.co.example.ecommerce_c.domain.Order;

/**
 * 注文完了メールを送信するサービス.
 *
 * @author takuya.aramaki
 */
@Service
public class OrderMailService {
	/** メール送信用オブジェクト */
	@Autowired
	private MailSender mailSender;

	/** Thymeleafのテンプレートエンジン */
	@Autowired
	private ThymeleafText thymeleafEngine;

	/**
	 * 注文完了メールを送信します.
	 *
	 * @param order 完了した注文
	 */
	@Async
	public void sendOrderCompleteMail(Order order) {
		String to = order.getDestinationEmail();
		String subject = "【ラクラクピザ】注文を承りました";
		thymeleafEngine.setVariable("order", order);
		String body = thymeleafEngine.process("mail/message.txt");

		sendMail(to, subject, body);
	}

	/**
	 * メールを送信します.
	 *
	 * @param to      宛先のメールアドレス
	 * @param subject メールのタイトル
	 * @param body    メール本文
	 */
	private void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}
}
