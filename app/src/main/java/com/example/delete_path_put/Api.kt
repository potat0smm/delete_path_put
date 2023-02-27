package com.example.delete_path_put

import android.accessibilityservice.GestureDescription.StrokeDescription
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface Api {

    @GET("https://jsonplaceholder.typicode.com/posts")
    fun getPosts(
        @Query("userId") userId: Int,
        @Query("id") id: Int): Call<ArrayList<Post>>

    @GET("posts")
    fun getPosts(
        @QueryMap parameters: HashMap<String, String>
    ):Call<ArrayList<Post>>

    @GET
    fun getComment(@Url url: String):Call<ArrayList<CommentResponse>>

    @GET("posts/{id}/comments")
    fun getComment(@Path("id")postId:Int): Call<ArrayList<CommentResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ):Call<CreatePostResponse>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPost(
        @Path("id") id:Int,
        @Field("userId") userId:Int,
        @Field("id") idField: Int,
        @Field("title") title: String?,
        @Field("body") text: String?
    ):Call<Post>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPost(
        @Path("id") id:Int,
        @Field("userId") userId: Int,
        @Field("id") idField: Int,
        @Field("title") title: String?,
        @Field("body") text: String?
    ):Call<Post>

    @DELETE("posts/{number}")
    fun deletePost(@Path("number")id: Int):Call<Void>

}