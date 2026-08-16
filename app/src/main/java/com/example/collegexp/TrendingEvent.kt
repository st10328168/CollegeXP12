package com.example.collegexp

data class TrendingEvent(
    val university: String,
    val event: String,
    val time: String,
    val logo: Int,
    val poster: Int,
    var likes: Int,
    var liked: Boolean = false
)

