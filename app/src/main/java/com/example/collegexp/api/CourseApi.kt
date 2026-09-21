package com.example.collegexp.api

import com.example.collegexp.model.Course
import retrofit2.Response
import retrofit2.http.*

interface CourseApi {

    @GET("api/courses")
    suspend fun getCourses(): Response<List<Course>>

    @GET("api/courses/{id}")
    suspend fun getCourse(@Path("id") id: Long): Response<Course>

    @POST("api/courses")
    suspend fun createCourse(
        @Body course: Course
    ): Response<Course>

    @PUT("api/courses/{id}")
    suspend fun updateCourse(
        @Path("id") id: Long,
        @Body course: Course
    ): Response<Course>

    @DELETE("api/courses/{id}")
    suspend fun deleteCourse(
        @Path("id") id: Long
    ): Response<Void>
}