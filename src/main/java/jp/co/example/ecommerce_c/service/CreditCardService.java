package jp.co.example.ecommerce_c.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.co.example.ecommerce_c.domain.Payment;
import jp.co.example.ecommerce_c.domain.PaymentResult;

@Service
public class CreditCardService {
	private static final String API_URL = "http://153.126.174.131:8080/sample-credit-card-web-api/credit-card/payment";

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	private RestTemplate restTemplate;

	public PaymentResult makePayment(Payment payment) {
		return restTemplate.postForObject(API_URL, payment, PaymentResult.class);
	}
	
	@PostConstruct
	private void initRestTemplate() {
		restTemplate = restTemplateBuilder.build();
	}
}
