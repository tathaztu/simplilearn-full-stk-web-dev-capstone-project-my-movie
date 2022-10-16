package com.mymovie.repository.specifications;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mymovie.domain.Movie;

public class MovieSpecifications {

	public static Specification<Movie> getSpecification(String strFilter) {
		//Build Specification with Employee Id and Filter Text
		return (root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);

			String strFilterWithLike = "%" + strFilter.toLowerCase() + "%";

			//Predicate for Employee Projects data
			Predicate predicateForMovie = criteriaBuilder.or(
				getLikePredicate(criteriaBuilder, root, "strGenreCode", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strLangCode", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strMovieName", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strDesc", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strStarring", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strDirectedBy", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strGenreCode", strFilterWithLike),
				getLikePredicate(criteriaBuilder, root, "strGenreCode", strFilterWithLike)
			);

			return predicateForMovie;
		};
	}

	private static Predicate getLikePredicate(
		CriteriaBuilder criteriaBuilder, Root<Movie> root, String strFieldName, String strLikeCriteria
	) {
		return
			criteriaBuilder.like(
				criteriaBuilder.lower(root.get(strFieldName)), strLikeCriteria
			);
	}
}
