package com.teamsparta.moamoa.domain.review.dto

import com.teamsparta.moamoa.domain.review.model.Review

data class ReviewResponseByList(
    var id: Long?,
    var title: String,
    var content: String,
    var imageUrl: String,
    var userName: String,
    var likes: Int, // 좋아요랑 연결
    var rating: Int,
) {
    companion object {
        fun toReviewResponseByList(reviews: List<Review>): List<ReviewResponseByList> {
            return reviews.map { review ->
                ReviewResponseByList(
                    id = review.id,
                    title = review.title,
                    content = review.content,
                    imageUrl = review.imageUrl ?: "",
                    userName = review.socialUser.nickname,
                    likes = review.likes, // 좋아요랑 연결
                    rating = review.rating,
                )
            }
        }
    }
}