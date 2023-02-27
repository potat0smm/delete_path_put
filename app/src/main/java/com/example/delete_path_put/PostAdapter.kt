package com.example.delete_path_put

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delete_path_put.databinding.ActivityMainBinding
import com.example.delete_path_put.databinding.ItemPostBinding

class PostAdapter (private val list:ArrayList<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>(){



    inner class PostViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemPostBinding.bind(itemView)
        fun bind(postResponse: Post){
            with(itemView){
                val text = "user ID:${postResponse.userId}\n"+
                    "id: ${postResponse.id}\n" +
                        "title: ${postResponse.title}\n" +
                        "text: ${postResponse.text}\n"
                binding.tvText.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

}