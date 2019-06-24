package jp.co.example.ecommerce_c.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.repository.ItemRepository;

/**
 * 商品関連機能の業務処理を行うサービス.
 * 
 * @author keita.tomooka
 *
 */
@Service
@Transactional
public class DisplayItemListService {
	@Autowired
	private ItemRepository repository;

	/**
	 * 全ての商品情報を取得する.
	 * 
	 * @return 商品情報のリスト
	 */
	public List<Item> findAll() {
		return repository.findAll();
	}

	/**
	 * 指定した名前を含む商品情報を取得する.
	 * 
	 * @param name 名前
	 * @return 商品情報のリスト
	 */
	public List<Item> findByLikeName(String name) {
		return repository.findByLikeName(name);
	}

	/**
	 * ページング用メソッド.
	 * 
	 * @param page         表示させたいページ数
	 * @param size         １ページに表示させる従業員数
	 * @param employeeList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の従業員一覧情報
	 */
	public Page<List<Item>> showListPaging(int page, int size, List<List<Item>> itemLists) {
		page--;
		int startItemCount = page * size;
		List<List<Item>> lists;

		if (itemLists.size() < startItemCount) {
			lists = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItemCount + size, itemLists.size());
			lists = itemLists.subList(startItemCount, toIndex);
		}

		Page<List<Item>> itemPage = new PageImpl<List<Item>>(lists, PageRequest.of(page, size), itemLists.size());
		return itemPage;
	}

	/**
	 * 価格が安い順に並べ替えるためのメソッド.
	 * 
	 * @param itemList 商品ののリスト
	 * @return 安い順に並べ替えられた商品のリスト
	 */
	public List<Item> priceLowOrder(List<Item> itemList) {
		Collections.sort(itemList, new Comparator<Item>() {
			public static final int ASC = 1;

			@Override
			public int compare(Item o1, Item o2) {
				int sortType = ASC;
				if (o1 == null && o2 == null) {
					return 0;
				} else if (o1 == null) {
					return 1 * sortType;
				} else if (o2 == null) {
					return -1 * sortType;
				}
				return (o1.getPriceL() - o2.getPriceL()) * sortType;
			}
		});
		return itemList;
	}

	/**
	 * 価格が高い順に並べ替えるためのメソッド.
	 * 
	 * @param itemList 商品ののリスト
	 * @return 高い順に並べ替えられた商品のリスト
	 */
	public List<Item> priceHighOrder(List<Item> itemList) {
		Collections.sort(itemList, new Comparator<Item>() {
			public static final int DESC = -1;

			@Override
			public int compare(Item o1, Item o2) {
				int sortType = DESC;
				if (o1 == null && o2 == null) {
					return 0;
				} else if (o1 == null) {
					return 1 * sortType;
				} else if (o2 == null) {
					return -1 * sortType;
				}
				return (o1.getPriceL() - o2.getPriceL()) * sortType;
			}
		});
		return itemList;
	}
	
	/**
	 * 商尾品情報をテーブル表示するための２次元配列を作る.
	 * 
	 * @param itemList
	 * @return 商品情報を格納した２次元配列
	 */
	public List<List<Item>> makeItemTable(List<Item> itemList){
		List<Item> item3List = new ArrayList<Item>();
		List<List<Item>> item3Lists = new ArrayList<>();
		for (int i = 0; i < itemList.size(); i++) {
			item3List.add(itemList.get(i));
			if (item3List.size() == 3) {
				item3Lists.add(item3List);
				item3List = new ArrayList<>();
			}
		}
		if (item3List.size() > 0) {
			item3Lists.add(item3List);
		}
		return item3Lists;
	}
}
