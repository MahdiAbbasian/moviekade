package com.android.moviekade.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviekade.R
import com.android.moviekade.databinding.ItemAnimationMovieBinding
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.framework.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class AnimationAdapter @Inject constructor(
    private val glide: RequestManager
    ) : RecyclerView.Adapter<AnimationAdapter.AnimViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<AnimationMovie>() {
        override fun areItemsTheSame(oldItem: AnimationMovie, newItem: AnimationMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AnimationMovie, newItem: AnimationMovie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    private var data : List<AnimationMovie>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimViewHolder {
        val view = ItemAnimationMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimViewHolder, position: Int) {
        val animationMovieResponse : AnimationMovie = data[position]
        holder.bind(glide, animationMovieResponse)
//        holder.setListener(object : ItemClickListener) {
//
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

     class AnimViewHolder(private val itemBinding: ItemAnimationMovieBinding) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null

         fun bind(glide: RequestManager, animationMovieResponse : AnimationMovie) {
             itemBinding.nameMovie.text = animationMovieResponse.name
             glide.load(animationMovieResponse.linkImg)
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