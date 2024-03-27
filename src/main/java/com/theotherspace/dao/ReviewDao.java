package com.theotherspace.dao;

import java.util.List;

import com.theotherspace.model.Review;

public interface ReviewDao extends GeneralDao<Review>{
 //Aggiunto commento e dao test per branch
	abstract public List<Review> findAllReviewsOfMovie(long userId);
}
