package com.example.mrmovieapplicationv3.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mrmovieapplicationv3.databinding.ActivityMainBinding
import com.example.mrmovieapplicationv3.view.ui.bookmark.BookmarkPageFragment
import com.example.mrmovieapplicationv3.view.ui.home.HomePageFragment
import com.example.mrmovieapplicationv3.viewmodel.MovieViewModel
import com.google.android.material.search.SearchView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homePageFragment = HomePageFragment()
    private val bookmarkPageFragment = BookmarkPageFragment()
    private val homeFragmentTag = "home_page_fragment_tag"
    private val bookmarkFragmentTag = "bookmark_page_fragment_tag"
    private val TAG = "search view"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

    private fun initializing() {
        addFragment(homePageFragment, homeFragmentTag)
        itemSelectedListener()
//        searchViewListener()
    }


    private fun itemSelectedListener() {
        var oldItem = bindingItemId(0)
        binding.bottomNavViewId.setOnItemSelectedListener {
            if (it.itemId != oldItem) {
                when (it.itemId) {
                    bindingItemId(0) -> {
                        handlingFragmentsAddOrReplace(homePageFragment, homeFragmentTag)
                        oldItem = bindingItemId(0)
                    }

                    bindingItemId(2) -> {
                        handlingFragmentsAddOrReplace(bookmarkPageFragment, bookmarkFragmentTag)
                        oldItem = bindingItemId(2)
                    }

                    else -> println("Nothing selected")
                }
            }
            true
        }
    }

    private fun bindingItemId(index: Int): Int = binding.bottomNavViewId.menu.getItem(index).itemId

    private fun replaceFragment(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }

    private fun addFragment(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }

    private fun handlingFragmentsAddOrReplace(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        var fragmentExists = fragmentManager.findFragmentByTag(fragTag)

        if (fragmentExists == null) {
            addFragment(fragment, fragTag)
        } else {
            replaceFragment(fragment, fragTag)
        }
    }
}