package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
