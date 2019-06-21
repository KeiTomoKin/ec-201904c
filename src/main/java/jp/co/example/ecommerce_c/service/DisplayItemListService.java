package jp.co.example.ecommerce_c.service;

import java.util.Collections;
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
	 * @param name 名前
	 * @return 	商品情報のリスト
	 */
	public List<Item> findByLikeName(String name) {
		return repository.findByLikeName(name);
	}
	/**
	 * ページング用メソッド.
	 * @param page 表示させたいページ数
	 * @param size １ページに表示させる従業員数
	 * @param employeeList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の従業員一覧情報
	 */
	public Page<List<Item>> showListPaging(int page, int size, List<List<Item>> itemLists) {
	    // 表示させたいページ数を-1しなければうまく動かない
	    page--;
	    // どの従業員から表示させるかと言うカウント値
	    int startItemCount = page * size;
	    // 絞り込んだ後の従業員リストが入る変数
	    List<List<Item>> lists;

	    if (itemLists.size() < startItemCount) {
	    	// (ありえないが)もし表示させたい従業員カウントがサイズよりも大きい場合は空のリストを返す
	        lists = Collections.emptyList();
	    } else {
	    	// 該当ページに表示させる従業員一覧を作成
	        int toIndex = Math.min(startItemCount + size,itemLists.size());
	        lists = itemLists.subList(startItemCount, toIndex);
	    }

	    // 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
	    Page<List<Item>> itemPage
	      = new PageImpl<List<Item>>(lists,PageRequest.of(page, size),itemLists.size());
	    return itemPage;
	}
}
