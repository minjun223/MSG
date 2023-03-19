package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val name: String?,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?
)