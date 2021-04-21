package com.android.demomvvm.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ItemSliderBinding
import com.android.demomvvm.model.data.SliderItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SliderAdapter(private val glide: RequestManager, private val listSlider: ArrayList<SliderItem>) : PagerAdapter() {
    private lateinit var itemSlider: ItemSliderBinding

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_slider, container, false)

        itemSlider.nameSlider.text = listSlider[position].name
        glide.load(listSlider[position].linkImg)
                .thumbnail(Glide.with(container.context).load(R.drawable.ic_popcorn).centerCrop())
                .transition(DrawableTransitionOptions().crossFade())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(itemSlider.imgSlider)
        container.addView(view)
        return view
    }
    override fun getCount(): Int {
        return listSlider.size       
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}