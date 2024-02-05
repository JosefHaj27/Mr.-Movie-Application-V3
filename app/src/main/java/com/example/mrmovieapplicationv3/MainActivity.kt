package com.example.mrmovieapplicationv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mrmovieapplicationv3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(HomePage())
        itemSelectedListener()
    }

    private fun itemSelectedListener()
    {
        binding.bottomNavViewId.setOnItemSelectedListener {
            when(it.itemId)
            {
                bindingItemId(0) -> replaceFragment(HomePage())
                bindingItemId(2) -> replaceFragment(BookmarkPage())
                else -> println("Nothing selected")
            }
            true
        }
    }

    private fun bindingItemId(index: Int): Int = binding.bottomNavViewId.menu.getItem(index).itemId

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