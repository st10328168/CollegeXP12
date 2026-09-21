package com.example.collegexp.model

data class Student(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String? = null
)