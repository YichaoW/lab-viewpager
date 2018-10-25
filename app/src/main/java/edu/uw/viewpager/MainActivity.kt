package edu.uw.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {


    private lateinit var searchFragment: SearchFragment
    private lateinit var detailFragment: DetailFragment
    private lateinit var listFragment: MovieListFragment
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchFragment = SearchFragment.newInstance()
        viewPager = findViewById<ViewPager>(R.id.viewPager)
        pagerAdapter = MoviePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
    }

    override fun onMovieSelected(movie: Movie) {
        detailFragment = DetailFragment.newInstance(movie)
        pagerAdapter!!.notifyDataSetChanged()
        viewPager!!.currentItem = 2
    }

    override fun onSearchSubmitted(searchTerm: String) {
        listFragment = MovieListFragment.newInstance(searchTerm)
        pagerAdapter!!.notifyDataSetChanged()
        viewPager!!.currentItem = 1

    }

    companion object {

        private val TAG = "MainActivity"
        val MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment"
        val MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment"
    }

    private inner class MoviePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return if (listFragment == null) {
                1
            } else if (detailFragment == null) {
                2
            } else {
                3
            }
        }

        override fun getItem(position: Int): Fragment? {
            return when (position) {
                0 -> searchFragment
                1 -> listFragment
                2 -> detailFragment
                else -> null
            }
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }
    }
}
