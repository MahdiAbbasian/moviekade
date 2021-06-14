package com.android.moviekade.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviekade.R
import com.android.moviekade.databinding.ItemSeriesBinding
import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.framework.utils.ItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class SeriesAdapter @Inject constructor(
    private val glide: RequestManager
    ): RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var data: List<Series>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val seriesResponse: Series = data[position]
        holder.bind(glide, seriesResponse)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class SeriesViewHolder(private val itemBinding: ItemSeriesBinding): RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private var listener: ItemClickListener? = null
        lateinit var glide: RequestManager

        fun bind(glide: RequestManager, seriesResponse: Series) {
            itemBinding.nameMovie.text = seriesResponse.name
            glide.load(seriesResponse.linkImg)
                .thumbnail(Glide.with(itemBinding.imgMovie).load(R.drawable.ic_popcorn).centerCrop())
                .transition(DrawableTransitionOptions().crossFade())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
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