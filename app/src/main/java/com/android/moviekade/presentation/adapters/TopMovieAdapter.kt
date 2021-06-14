package com.android.moviekade.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviekade.R
import com.android.moviekade.databinding.ItemTopMovieBinding
import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.framework.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class TopMovieAdapter @Inject constructor(
    private val glide: RequestManager
    ) : RecyclerView.Adapter<TopMovieAdapter.TopMovieViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<TopMovie>() {
        override fun areItemsTheSame(oldItem: TopMovie, newItem: TopMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TopMovie, newItem: TopMovie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var data: List<TopMovie>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val view = ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        val topMovieResponse: TopMovie = data[position]
        holder.bind(glide, topMovieResponse)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TopMovieViewHolder(private val itemBinding: ItemTopMovieBinding) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null

        fun bind(glide: RequestManager, topMovieResponse: TopMovie) {
            itemBinding.nameMovie.text = topMovieResponse.name
            glide.load(topMovieResponse.linkImg)
                .thumbnail(Glide.with(itemBinding.imgMovie).load(R.drawable.ic_popcorn).centerCrop())
                .transition(DrawableTransitionOptions().crossFade())
                .into(itemBinding.imgMovie)
            itemBinding.root.setOnClickListener(this)
        }

        fun setListener(listener: ItemClickListener?) {
            this.listener = listener
        }

        override fun onClick(view: View) {
            listener?.onClick(view)
        }
    }

}