package com.android.demomvvm.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ItemNewMovieBinding
import com.android.demomvvm.model.data.InformationHomeItemNewMovie
import com.android.demomvvm.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class NewMovieAdapter(private val data: List<InformationHomeItemNewMovie>) :
    RecyclerView.Adapter<NewMovieAdapter.NewMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieViewHolder {
        val view = ItemNewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewMovieViewHolder, position: Int) {
        val informationHomeItemNewMovie: InformationHomeItemNewMovie = data[position]
        holder.bind(informationHomeItemNewMovie)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class NewMovieViewHolder(private val itemBinding: ItemNewMovieBinding): RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null
        lateinit var glide: RequestManager

        fun bind(informationHomeItemNewMovie: InformationHomeItemNewMovie) {
            itemBinding.nameMovie.text = informationHomeItemNewMovie.name
            glide.load(informationHomeItemNewMovie.linkImg)
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