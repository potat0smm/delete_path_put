package com.example.delete_path_put

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delete_path_put.databinding.ItemPostBinding

class CommentAdapter (private val listComment: ArrayList<CommentResponse>):RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    inner class CommentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val binding = ItemPostBinding.bind(itemView)
        fun bind(commentResponse: CommentResponse){
            with(itemView){
                val text = "postId: ${commentResponse.postId}\n" +
                        "id: ${commentResponse.id}\n"+
                        "name:${commentResponse.name}\n" +
                        "email: ${commentResponse.email}\n" +
                        "body: ${commentResponse.body}"
                binding.tvText.text = text
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = listComment.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

}