package com.android.moviekade.presentation.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviekade.R
import com.android.moviekade.presentation.adapters.*
import com.android.moviekade.business.data.remote.response.SliderItemResponse
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.databinding.*
import com.android.moviekade.presentation.MainState
import com.android.moviekade.presentation.viewModel.*
import com.android.moviekade.framework.utils.BaseApplication.Companion.fBold
import com.android.moviekade.framework.utils.BaseApplication.Companion.fRegular
import com.android.moviekade.framework.utils.toast
import com.bumptech.glide.RequestManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.MutableList as MutableList

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHeaderHomeBinding: NavHeaderHomeBinding
    private lateinit var itemProgressBarBinding :ItemProgressbarBinding
    private lateinit var sliderViewModel: SliderViewModel
    private val animationViewModel: AnimationViewModel by viewModels()
    private val topMovieViewModel: TopMovieViewModel by viewModels()
    private val newMovieViewModel: NewMovieViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    var itemSliderResponse: ArrayList<SliderItemResponse> = ArrayList()

    @Inject
    lateinit var sliderAdapter: SliderAdapter
    @Inject
    lateinit var animationAdapter: AnimationAdapter
    @Inject
    lateinit var topMovieAdapter: TopMovieAdapter
    @Inject
    lateinit var newMovieAdapter: NewMovieAdapter
    @Inject
    lateinit var seriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sliderViewModel = ViewModelProvider(this).get(SliderViewModel::class.java)

        toast("Hello")
        /*config Toolbar*/
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.btnMenu.setOnClickListener(View.OnClickListener {
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
        seriesViewModel.state.observe(this, {state->
            when(state){
                is MainState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,state.text,Toast.LENGTH_LONG).show()
                }
                is MainState.Loaded -> {
                    displayProgressBar(false)
                    binding.recSeries.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.recSeries.setHasFixedSize(true)
                    seriesViewModel.getSeries().observe(this, Observer {
                        seriesAdapter.differ.submitList(state.data as MutableList<Series>)
                        Log.e("INSIDE", state.data as String)
                    })
                    binding.recSeries.adapter = seriesAdapter
                }
                MainState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun setUpNewMovie() {
        newMovieViewModel.state.observe(this, {state->
            when(state){
                is MainState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,state.text,Toast.LENGTH_LONG).show()
                }
                is MainState.Loaded -> {
                    displayProgressBar(false)
                    binding.recNewMovie.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.recNewMovie.setHasFixedSize(true)
                    newMovieViewModel.getNewMovie().observe(this, Observer {
                        newMovieAdapter.differ.submitList(state.data as MutableList<NewMovie>)
                    })
                    binding.recNewMovie.adapter = newMovieAdapter
                }
                MainState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun setUpTopMovie() {
        topMovieViewModel.state.observe(this, {state->
            when(state){
                is MainState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,state.text,Toast.LENGTH_LONG).show()
                }
                is MainState.Loaded -> {
                    displayProgressBar(false)
                    binding.recTopMovie.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.recTopMovie.setHasFixedSize(true)
                    topMovieViewModel.getTopMovie().observe(this, Observer {
                        topMovieAdapter.differ.submitList(state.data as MutableList<TopMovie>)
                    })
                    binding.recTopMovie.adapter = topMovieAdapter
                }
                MainState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun setUpAnimationMovie() {
        animationViewModel.state.observe(this, {state->
            when(state){
                is MainState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,state.text,Toast.LENGTH_LONG).show()
                }
                is MainState.Loaded -> {
                    displayProgressBar(false)
                    binding.recAnimationMovie.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                binding.recAnimationMovie.setHasFixedSize(true)
                animationViewModel.getAnimationMovie().observe(this, Observer {
                    animationAdapter.differ.submitList(state.data as MutableList<AnimationMovie>)
                })
                    binding.recAnimationMovie.adapter = animationAdapter
                }
                MainState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun setUpSlider() {
        sliderViewModel.state.observe(this, {state->
            when(state){
                is MainState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,state.text,Toast.LENGTH_LONG).show()
                }
                is MainState.Loaded -> {
                    displayProgressBar(false)
                    sliderViewModel.getSlider().observe(this, Observer {
                        sliderAdapter = SliderAdapter(state.data as RequestManager)
                    })
                    binding.also {
                        it.productSlider.adapter = sliderAdapter
                        it.tabProductSlider.setupWithViewPager(binding.productSlider, true)
                    }

                    val timerSlider = TimerSlider()
                    val timer = Timer()
                    timer.scheduleAtFixedRate(timerSlider, 3000, 6000)
                }
                MainState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    inner class TimerSlider : TimerTask() {
        override fun run() {
            runOnUiThread(Runnable {
                if (binding.productSlider.currentItem < itemSliderResponse.size - 1) {
                    binding.productSlider.currentItem = binding.productSlider.currentItem + 1
                } else {
                    binding.productSlider.currentItem = 0
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configHeaderDrawer() {
        val view = binding.navView.getHeaderView(0)
        navHeaderHomeBinding = NavHeaderHomeBinding.bind(view)
        val circleProfile = navHeaderHomeBinding.circleProfile
        val profileName = navHeaderHomeBinding.txtNavProfileName
        val profileNumber = navHeaderHomeBinding.txtNavProfileNum
        circleProfile.circleBackgroundColor = R.mipmap.ic_launcher
        profileName.text = "مهدی عباسیان"
        profileNumber.text = "09109615748"
        profileName.typeface = fBold
        profileNumber.typeface = fRegular
    }

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
    
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
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

    private fun displayProgressBar(isDisplayed: Boolean) {
        itemProgressBarBinding.pb.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}
