package com.example.delete_path_put

import com.google.gson.annotations.SerializedName

data class CreatePostResponse(

    val userId:String?,
    val id: Int,
    val title: String?,
    @SerializedName("body")
    val text: String?
)
