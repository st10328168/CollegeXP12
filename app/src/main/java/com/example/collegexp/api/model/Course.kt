package com.example.collegexp.model

data class Course(
    val id: Long? = null,
    val courseCode: String,
    val courseName: String,
    val description: String? = null
)