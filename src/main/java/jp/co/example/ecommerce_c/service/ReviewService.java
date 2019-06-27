package jp.co.example.ecommerce_c.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Review;
import jp.co.example.ecommerce_c.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public void insert(ReviewForm form) {
		Review review = new Review();
		review.setName(form.getName());
		review.setContent(form.getContent());
		review.setItemId(Integer.parseInt(form.getItemId()));
		review.setEvaluation(Integer.parseInt(form.getEvaluation()));
		reviewRepository.insert(review);
	}
	
	public double calcAvgEvaluation(Integer itemId) {
		try {
			return reviewRepository.averageByItemId(itemId);
		}catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public List<Review> findByItemId(Integer itemId) {
		List<Review>reviewList=reviewRepository.findByItemId(itemId);
		return reviewList;
	}
}
