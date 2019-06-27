package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.service.ReviewForm;
import jp.co.example.ecommerce_c.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@RequestMapping("/insert")
	public String insertreview(ReviewForm form) {
		reviewService.insert(form);
		return "redirect:/item/detail?id="+form.getItemId();
	}
}
