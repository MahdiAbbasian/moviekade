package com.android.demomvvm.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ItemTopMovieBinding
import com.android.demomvvm.model.data.InformationHomeItemTopMovie
import com.android.demomvvm.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class TopMovieAdapter(private val data: List<InformationHomeItemTopMovie>) :
    RecyclerView.Adapter<TopMovieAdapter.TopMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val view = ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        val informationHomeItemTopMovie: InformationHomeItemTopMovie = data[position]
        holder.bind(informationHomeItemTopMovie)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TopMovieViewHolder(private val itemBinding: ItemTopMovieBinding) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null
        lateinit var glide: RequestManager

        fun bind(informationHomeItemTopMovie: InformationHomeItemTopMovie) {
            itemBinding.nameMovie.text = informationHomeItemTopMovie.name
            glide.load(informationHomeItemTopMovie.linkImg)
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