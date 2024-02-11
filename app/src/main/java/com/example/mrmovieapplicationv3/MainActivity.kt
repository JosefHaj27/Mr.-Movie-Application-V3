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

        initializing()
    }

    private fun initializing()
    {
        addFragment(homePageFragment, "home_page_fragment_tag")
        itemSelectedListener()
    }

    private fun itemSelectedListener()
    {
        binding.bottomNavViewId.setOnItemSelectedListener {
            when(it.itemId)
            {
                //  TODO:: fix this:: DONE
                bindingItemId(0) -> handlingFragmentsAddOrReplace(homePageFragment, "home_page_fragment_tag")
                bindingItemId(2) -> handlingFragmentsAddOrReplace(bookmarkPageFragment, "bookmark_page_fragment_tag")
                else -> println("Nothing selected")
            }
            true
        }
    }

    private fun bindingItemId(index: Int): Int = binding.bottomNavViewId.menu.getItem(index).itemId

    // TODO:: check replace by tag:: Still!
    private fun replaceFragment(fragment: Fragment, fragTag: String)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }

    private fun addFragment(fragment: Fragment, fragTag: String)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }

    private fun handlingFragmentsAddOrReplace(fragment: Fragment, fragTag: String)
    {
        val fragmentManager = supportFragmentManager
        val allFragments = fragmentManager.fragments
        var fragmentExists = false

        println("all fragments are $allFragments")
        if (!fragmentExists)
        {
            for (frag in allFragments)
            {
                if (frag.tag == fragment.tag)
                {
                    fragmentExists = true
                    replaceFragment(fragment, fragTag)
                    println("the current fragment is $frag")
                    println("all fragments are (inside if statment) $allFragments")
                    return
                }
            }
            addFragment(fragment, fragTag)
        }
    }




}