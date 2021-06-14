package com.android.moviekade.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.android.moviekade.R
import com.android.moviekade.databinding.ItemSliderBinding
import com.android.moviekade.business.domain.entity.Slider
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class SliderAdapter @Inject constructor(
    private val glide: RequestManager
    ) : PagerAdapter() {

    private lateinit var itemSlider: ItemSliderBinding
    lateinit var data: List<Slider>

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_slider, container, false)

        itemSlider.nameSlider.text = data[position].name
        glide.load(data[position].linkImg)
                .thumbnail(Glide.with(container.context).load(R.drawable.ic_popcorn).centerCrop())
                .transition(DrawableTransitionOptions().crossFade())
                .into(itemSlider.imgSlider)
        container.addView(view)
        return view
    }
    override fun getCount(): Int {
        return data.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}