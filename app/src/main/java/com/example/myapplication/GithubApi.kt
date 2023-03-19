package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Call<GithubUser>
}