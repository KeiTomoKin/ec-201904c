package jp.co.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.service.DisplayItemListService;

/**
 * 商品関連機能の処理の制御を行うコントローラー.
 * 
 * @author keita.tomooka
 *
 */
@Controller
@RequestMapping("/displayItemList")
public class DisplayItemListController {
	@Autowired
	DisplayItemListService service;

	/**
	 * 商品一覧画面にフォワードする処理を行う.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品リストの画面「item_list.html」
	 */
	@RequestMapping("/showList")
	public String service(Model model) {
		List<Item> itemList = service.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}

}
