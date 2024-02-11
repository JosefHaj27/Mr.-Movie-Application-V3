package com.example.mrmovieapplicationv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mrmovieapplicationv3.databinding.ActivityMainBinding
import com.example.mrmovieapplicationv3.ui.bookmark.BookmarkPageFragment
import com.example.mrmovieapplicationv3.ui.home.HomePageFragment

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val homePageFragment = HomePageFragment()
    private val bookmarkPageFragment = BookmarkPageFragment()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//  TODO::  move this to initialize method:: DONE
        initializing()
    }

    private fun initializing()
    {
        addFragment(homePageFragment)
        itemSelectedListener()
    }

    private fun itemSelectedListener()
    {
        binding.bottomNavViewId.setOnItemSelectedListener {
            when(it.itemId)
            {
                //  TODO:: fix this:: DONE
                bindingItemId(0) -> replaceFragment(homePageFragment)
                bindingItemId(2) -> replaceFragment(bookmarkPageFragment)
                else -> println("Nothing selected")
            }
            true
        }
    }

    private fun bindingItemId(index: Int): Int = binding.bottomNavViewId.menu.getItem(index).itemId

    // TODO:: check replace by tag:: Still!
    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayoutId.id, fragment)
        fragmentTransaction.commit()
    }

    private fun addFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frameLayoutId.id, fragment)
        fragmentTransaction.commit()
    }
}