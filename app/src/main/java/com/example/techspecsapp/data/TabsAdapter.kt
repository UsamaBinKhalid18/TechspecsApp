package com.example.techspecsapp.data

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.techspecsapp.BookmarkFragment
import com.example.techspecsapp.RecentFragment
import com.example.techspecsapp.SearchFragment

class TabsAdapter(fragmentActivity: FragmentActivity, private val repository: Repository) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> SearchFragment(repository)
        2->BookmarkFragment(repository)
        else -> RecentFragment(repository)
    }
}