package com.example.collegexp
data class Post(
    val image: Int,
    val university: String,
    val caption: String,
    var likes: Int,
    var comments: Int
)