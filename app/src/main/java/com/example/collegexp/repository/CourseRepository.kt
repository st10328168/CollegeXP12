package com.example.collegexp.repository

import com.example.collegexp.api.RetrofitClient
import com.example.collegexp.model.Course

class CourseRepository {

    suspend fun getCourses(): List<Course> {
        val response = RetrofitClient.courseApi.getCourses()

        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to load courses: ${response.code()}")
        }
    }
}