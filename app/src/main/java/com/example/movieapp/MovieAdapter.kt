package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var onClickDelete: OnClickDelete? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"${differ.currentList[position].movieTitle}",
                Toast.LENGTH_SHORT).show()
        }

        holder.itemView.btn_delete.setOnClickListener {
            onClickDelete?.onDelete(differ.currentList[position])
        }


        holder.itemView.tv_title.text = differ.currentList[position].movieTitle
        holder.itemView.tv_description.text = differ.currentList[position].movieDescription
        if (differ.currentList[position].moviePhoto != null) {
            holder.itemView.im_photo.setImageResource(differ.currentList[position].moviePhoto!!)

        }

    }

    override fun getItemCount(): Int = differ.currentList.size


    fun submitList(list: List<Movie>) {
        differ.submitList(list)
    }

    interface OnClickDelete {

        fun onDelete(item: Movie)
    }

    fun deleteItem(index:Int) {

    }

}