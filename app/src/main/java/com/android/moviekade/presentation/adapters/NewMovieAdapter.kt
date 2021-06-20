package com.android.moviekade.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviekade.R
import com.android.moviekade.databinding.ItemNewMovieBinding
import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.framework.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class NewMovieAdapter @Inject constructor(
    private val glide: RequestManager
    ) : RecyclerView.Adapter<NewMovieAdapter.NewMovieViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<NewMovie>() {
        override fun areItemsTheSame(oldItem: NewMovie, newItem: NewMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewMovie, newItem: NewMovie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    private var data: List<NewMovie>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieViewHolder {
        val view = ItemNewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewMovieViewHolder, position: Int) {
        val newMovieResponse: NewMovie = data[position]
        holder.bind(glide, newMovieResponse)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class NewMovieViewHolder(private val itemBinding: ItemNewMovieBinding): RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null

        fun bind(glide: RequestManager, newMovieResponse: NewMovie) {
            itemBinding.nameMovie.text = newMovieResponse.name
            glide.load(newMovieResponse.linkImg)
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