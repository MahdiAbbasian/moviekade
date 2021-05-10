package com.android.demomvvm.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ItemSeriesBinding
import com.android.demomvvm.model.data.remote.response.SeriesItemResponse
import com.android.demomvvm.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SeriesAdapter(private val data: List<SeriesItemResponse>):
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val seriesItemResponse: SeriesItemResponse = data[position]
        holder.bind(seriesItemResponse)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class SeriesViewHolder(private val itemBinding: ItemSeriesBinding): RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null
        lateinit var glide: RequestManager

        fun bind(seriesItemResponse: SeriesItemResponse) {
            itemBinding.nameMovie.text = seriesItemResponse.name
            glide.load(seriesItemResponse.linkImg)
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