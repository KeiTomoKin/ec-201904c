package jp.co.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.service.DisplayItemListService;

/**
 * 商品関連機能の処理の制御を行うコントローラー.
 * 
 * @author keita.tomooka
 * @author kazuya.makida
 *
 */
@Controller
@RequestMapping("/")
public class DisplayItemListController {
	@Autowired
	DisplayItemListService service;

	/** 商品を表示する際の行の数 */
	private static final int VIEW_SIZE = 3;

	/**
	 * 商品一覧画面にフォワードする処理を行う.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品リストの画面「item_list.html」
	 */
	@RequestMapping("")
	public String showList(Model model, Integer page, String sortOrder) {
		if (page == null) {
			page = 1;
		}
		List<Item> itemList = service.findAll();
		if (sortOrder != null) {
			if (sortOrder.equals("ASC")) {
				itemList = service.priceLowOrder(itemList);
			} else if (sortOrder.equals("DESC")) {
				itemList = service.priceHighOrder(itemList);
			}
		}
		// 名前のみリストの作成
		List<String> itemNameList = new ArrayList<String>();
		for (Item item : itemList) {
			String name = item.getName();
			itemNameList.add(name);
		}
		List<List<Item>> item3Lists = service.makeItemTable(itemList);
		Page<List<Item>> itemPage = service.showListPaging(page, VIEW_SIZE, item3Lists);
		List<Integer> pageNumbers = calcPageNumbers(itemPage);
		model.addAttribute("itemPage", itemPage);
		model.addAttribute("itemNameList", itemNameList);
		model.addAttribute("page", page);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("item3Lists", item3Lists);
		model.addAttribute("sortOrder", sortOrder);
		return "item_list";
	}

	/**
	 * 曖昧検索を行い商品一覧画面にフォワードする処理を行う.
	 * 
	 * @param model リクエストスコープ
	 * @param name  検索値
	 * @return 商品リストの画面「item_list.html」
	 */
	@RequestMapping("/findName")
	public String findName(Model model, String name, Integer page, String sortOrder) {
		if (page == null) {
			page = 1;
		}
		if (name == "") {
			model.addAttribute("nullNameCheck", true);
			return showList(model, 1, sortOrder);
		}
		List<Item> itemList = service.findByLikeName(name);
		if (itemList.size() == 0) {
			model.addAttribute("noResult", true);
			return showList(model, 1, sortOrder);
		}
		if (sortOrder != null) {
			if (sortOrder.equals("ASC")) {
				itemList = service.priceLowOrder(itemList);
			} else if (sortOrder.equals("DESC")) {
				itemList = service.priceHighOrder(itemList);
			}
		}
		List<List<Item>> item3Lists = service.makeItemTable(itemList);
		Page<List<Item>> itemPage = service.showListPaging(page, VIEW_SIZE, item3Lists);
		List<Integer> pageNumbers = calcPageNumbers(itemPage);
		itemList = service.findAll();
		// 名前のみリストの作成
		List<String> itemNameList = new ArrayList<String>();
		for (Item item : itemList) {
			String itemName = item.getName();
			itemNameList.add(itemName);
		}
		model.addAttribute("itemNameList", itemNameList);
		model.addAttribute("itemPage", itemPage);
		model.addAttribute("page", page);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("name", name);
		model.addAttribute("item3Lists", item3Lists);
		model.addAttribute("sortOrder", sortOrder);
		return "item_list";
	}

	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model    モデル
	 * @param itemPage ページング情報
	 */
	private List<Integer> calcPageNumbers(Page<List<Item>> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}
}
