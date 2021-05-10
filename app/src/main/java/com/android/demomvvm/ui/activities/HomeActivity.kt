package com.android.demomvvm.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.demomvvm.R
import com.android.demomvvm.databinding.ActivityHomeBinding
import com.android.demomvvm.databinding.ContentHomeBinding
import com.android.demomvvm.databinding.ItemSliderBinding
import com.android.demomvvm.databinding.NavHeaderHomeBinding
import com.android.demomvvm.model.adapters.*
import com.android.demomvvm.model.data.remote.response.SliderItemResponse
import com.android.demomvvm.model.database.MovieKadeDB
import com.android.demomvvm.ui.viewModel.*
import com.android.demomvvm.utils.BaseApplication.Companion.fBold
import com.android.demomvvm.utils.BaseApplication.Companion.fRegular
import com.android.demomvvm.utils.toast
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var contentHomeBinding: ContentHomeBinding
    private lateinit var itemSliderBinding: ItemSliderBinding
    private lateinit var navHeaderHomeBinding: NavHeaderHomeBinding
    lateinit var sliderViewModel: SliderViewModel
    lateinit var animationViewModel: AnimationViewModel
    lateinit var topMovieViewModel: TopMovieViewModel
    lateinit var newMovieViewModel: NewMovieViewModel
    lateinit var seriesViewModel: SeriesViewModel
    var itemSliderResponse: ArrayList<SliderItemResponse> = ArrayList()
    var sliderAdapter: SliderAdapter? = null
    var animationAdapter: AnimationAdapter? = null
    var topMovieAdapter: TopMovieAdapter? = null
    var newMovieAdapter: NewMovieAdapter? = null
    var seriesAdapter: SeriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentHomeBinding = ContentHomeBinding.inflate(layoutInflater)
        sliderViewModel = ViewModelProvider(this).get(SliderViewModel::class.java)

        toast("Hello")
        /*config Toolbar*/
        setSupportActionBar(contentHomeBinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        contentHomeBinding.btnMenu.setOnClickListener(View.OnClickListener {
            val drawerLockMode = binding.drawerLayout.getDrawerLockMode(GravityCompat.END)
            if (binding.drawerLayout.isDrawerVisible(GravityCompat.END) && drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        })
        //region call functions
        configHeaderDrawer()
        setUpSlider()
        setUpAnimationMovie()
        setUpTopMovie()
        setUpNewMovie()
        setUpSeries()
        //endregion
    }

    private fun setUpSeries() {
        contentHomeBinding.recSeries.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        contentHomeBinding.recSeries.setHasFixedSize(true)
        seriesViewModel.seriesLiveDataResponse.observe(this, Observer {
            seriesAdapter = SeriesAdapter(it)
        })
        contentHomeBinding.recSeries.adapter = seriesAdapter
    }

    private fun setUpNewMovie() {
        contentHomeBinding.recNewMovie.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        contentHomeBinding.recNewMovie.setHasFixedSize(true)
        newMovieViewModel.newMovieResponseLiveData.observe(this, Observer {
            newMovieAdapter = NewMovieAdapter(it)
        })
        contentHomeBinding.recNewMovie.adapter = newMovieAdapter
    }

    private fun setUpTopMovie() {
        contentHomeBinding.recTopMovie.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
     )
        contentHomeBinding.recTopMovie.setHasFixedSize(true)
        topMovieViewModel.topMovieResponseLiveData.observe(this, Observer {
            topMovieAdapter = TopMovieAdapter(it)
        })
        contentHomeBinding.recTopMovie.adapter = topMovieAdapter
    }

    private fun setUpAnimationMovie() {
        contentHomeBinding.recAnimationMovie.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        contentHomeBinding.recAnimationMovie.setHasFixedSize(true)
        animationViewModel.animationResponseLiveData.observe(this, Observer {
            animationAdapter = AnimationAdapter(it)
        })
        contentHomeBinding.recAnimationMovie.adapter = animationAdapter
    }

    private fun setUpSlider() {
            sliderViewModel.sliderLiveDataResponse.observe(this, Observer { listSlider ->
                sliderAdapter = SliderAdapter(Glide.with(this), listSlider as ArrayList)
            })
        contentHomeBinding.also {
            it.productSlider.adapter = sliderAdapter
            it.tabProductSlider.setupWithViewPager(contentHomeBinding.productSlider, true)
        }

        val timerSlider = TimerSlider()
        val timer = Timer()
        timer.scheduleAtFixedRate(timerSlider, 3000, 6000)
    }

    inner class TimerSlider : TimerTask() {
        override fun run() {
            runOnUiThread(Runnable {
                if (contentHomeBinding.productSlider.getCurrentItem() < itemSliderResponse.size - 1) {
                    contentHomeBinding.productSlider.setCurrentItem(contentHomeBinding.productSlider.getCurrentItem() + 1)
                } else {
                    contentHomeBinding.productSlider.setCurrentItem(0)
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configHeaderDrawer() {
        val view = binding.navView.getHeaderView(0)
        val circleProfile = navHeaderHomeBinding.circleProfile
        val profileName = navHeaderHomeBinding.txtNavProfileName
        val profileNumber = navHeaderHomeBinding.txtNavProfileNum
        profileName.text = "مهدی العباسیان"
        profileNumber.text = "09109615748"
        profileName.typeface = fBold
        profileNumber.typeface = fRegular
    }

    /*For Exit binding.drawerLayout*/
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_drawer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    /*For implement setNavigationItemSelectedListener*/
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        when (id) {
            R.id.navHome -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            }
            R.id.navCategory -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            }
            R.id.navProfile -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            }
            R.id.navSupport -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            }
            R.id.navAboutTeam -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            }
            R.id.navQuestions -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
                onBackPressed()
            }
            R.id.navQuit -> if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
                onBackPressed()
            }
        }
        return true
    }
}
