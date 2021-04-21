package com.android.demomvvm.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ItemAnimationMovieBinding
import com.android.demomvvm.model.data.InformationHomeItemAnimation
import com.android.demomvvm.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class AnimationAdapter(private val data: List<InformationHomeItemAnimation>) :
    RecyclerView.Adapter<AnimationAdapter.AnimViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimViewHolder {
        val view = ItemAnimationMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimViewHolder, position: Int) {
        val informationHomeItemAnimation: InformationHomeItemAnimation = data[position]
        holder.bind(informationHomeItemAnimation)
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
        lateinit var glide: RequestManager

         fun bind(informationHomeItemAnimation: InformationHomeItemAnimation) {
             itemBinding.nameMovie.text = informationHomeItemAnimation.name
             glide.load(informationHomeItemAnimation.linkImg)
                 .thumbnail(Glide.with(itemBinding.imgMovie).load(R.drawable.ic_popcorn).centerCrop())
                 .transition(DrawableTransitionOptions().crossFade())
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                 .into(itemBinding.imgMovie)
             itemBinding.root.setOnClickListener(this)
         }

//        fun setListener(listener: ItemClickListener?) {
//            this.listener = listener
//        }

        override fun onClick(view: View) {
            listener?.onClick(view)
        }
    }

}