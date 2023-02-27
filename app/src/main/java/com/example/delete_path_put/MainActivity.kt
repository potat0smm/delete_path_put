package com.example.delete_path_put

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delete_path_put.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Post>()
    private val listComment = ArrayList<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPosts()
        createPost()
        showComments()
        updatePost()
        deletePost()
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(1).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                binding.tvResponseCode.text = response.code().toString()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                binding.tvResponseCode.text = t.message
            }

        })
    }

    private fun updatePost() {
        RetrofitClient.instance.patchPost(
            5,
            4,
            5,
            null,
            "LOL"
        ).enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                binding.tvResponseCode.text = response.code().toString()
                val responseText = "Response code:${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.text}\n" +
                        "userId:${response.body()?.userId}\n" +
                        "id:${response.body()?.id}"
                binding.tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                binding.tvResponseCode.text = t.message
            }

        })
    }

    private fun showComments() {
        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getComment("posts/2/comments").enqueue(object : Callback<ArrayList<CommentResponse>>{
            override fun onResponse(
                call: Call<ArrayList<CommentResponse>>,
                response: Response<ArrayList<CommentResponse>>
            ) {
                binding.tvResponseCode.text = response.code().toString()
                response.body()?.let {listComment.addAll(it)}

                val adapter = CommentAdapter(listComment)
                binding.rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                binding.tvResponseCode.text = t.message
            }

        })
    }

    private fun createPost() {
        RetrofitClient.instance.createPost(
            23,
            "LOL",
            "KEK"
        ).enqueue(object : Callback<CreatePostResponse>{
            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
            binding.tvResponseCode.text = t.message
        }
            override fun onResponse(call: Call<CreatePostResponse>, response: Response<CreatePostResponse>) {
                val responseText = "Response code:${response.code()}\n" +
                "title: ${response.body()?.title}\n" +
                "body: ${response.body()?.text}\n" +
                        "userId:${response.body()?.userId}\n" +
                        "id:${response.body()?.id}"
                        binding.tvResponseCode.text = responseText
            }
        })
    }

    private fun showPosts() {
        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)

        val parameters = HashMap<String,String>()
        parameters["userId"] = "4"
        parameters["id"] = "32"

        RetrofitClient.instance.getPosts(parameters).enqueue(object: Callback<ArrayList<Post>>{
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                val responseCode = response.code().toString()
                binding.tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it)}

                val adapter = PostAdapter(list)
                binding.rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {

            }
        })
    }
}