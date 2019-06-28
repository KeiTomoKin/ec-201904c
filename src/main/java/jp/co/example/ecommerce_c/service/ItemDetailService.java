package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.repository.ItemRepository;
import jp.co.example.ecommerce_c.repository.ReviewRepository;
import jp.co.example.ecommerce_c.repository.ToppingRepository;

@Service
@Transactional
public class ItemDetailService {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	/**
	 * 商品詳細,、コメントを取得します.
	 *
	 * @param id 取得する商品のID
	 * @return 商品詳細
	 */
	public Item getDetail(Integer id) {
		Item item = itemRepository.load(id);
		item.setToppingList(toppingRepository.findAll());
		item.setReviewList(reviewRepository.findByItemId(id));
		if (item.getReviewList().size()!=0) {
			item.setEvaluation(reviewRepository.averageByItemId(id));
		}else {
			item.setEvaluation(0);
		}
		return item;
	}
}
