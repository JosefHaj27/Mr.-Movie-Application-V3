package com.example.mrmovieapplicationv3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mrmovieapplicationv3.databinding.FragmentBookmarkPageBinding

class BookmarkPage : Fragment()
{
    private var _binding: FragmentBookmarkPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}