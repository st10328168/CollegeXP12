package com.example.collegexp.api

import com.example.collegexp.model.Student
import retrofit2.Response
import retrofit2.http.*

interface StudentApi {

    @GET("api/students")
    suspend fun getStudents(): Response<List<Student>>

    @GET("api/students/{id}")
    suspend fun getStudent(@Path("id") id: Long): Response<Student>

    @POST("api/students")
    suspend fun createStudent(
        @Body student: Student
    ): Response<Student>

    @PUT("api/students/{id}")
    suspend fun updateStudent(
        @Path("id") id: Long,
        @Body student: Student
    ): Response<Student>

    @DELETE("api/students/{id}")
    suspend fun deleteStudent(
        @Path("id") id: Long
    ): Response<Void>
}