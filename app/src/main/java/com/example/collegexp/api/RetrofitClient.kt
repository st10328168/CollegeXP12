package com.example.collegexp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val studentApi: StudentApi by lazy {
        retrofit.create(StudentApi::class.java)
    }

    val courseApi: CourseApi by lazy {
        retrofit.create(CourseApi::class.java)
    }
}