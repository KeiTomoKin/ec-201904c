package jp.co.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.domain.Review;
import jp.co.example.ecommerce_c.service.ItemDetailService;
import jp.co.example.ecommerce_c.service.ReviewService;

/**
 * 商品詳細に関するコントローラークラス.
 *
 * @author takuya.aramaki
 */
@Controller
@RequestMapping("/item")
public class ItemDetailController {
	@Autowired
	private ItemDetailService itemDetailService;
	@Autowired
	private ReviewService reviewService;
	/**
	 * 商品詳細を表示します.
	 *
	 * @param id 表示する商品のID
	 * @param model リクエストスコープ
	 * @return 商品詳細画面へフォワード
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Item item = itemDetailService.getDetail(id);
		if (item == null) {
			// TODO 404エラーが出たほうが良いのでは
			throw new RuntimeException("指定したIDの商品がありませんでした");
		}
		List<Review> reviewList = reviewService.findByItemId(id);
		item.setReviewList(reviewList);
		item.setEvaluation(reviewService.calcAvgEvaluation(id));
		model.addAttribute("item", item);
		return "item_detail";
	}
}
