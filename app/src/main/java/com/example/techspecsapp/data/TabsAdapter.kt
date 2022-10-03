package com.example.techspecsapp.data

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.techspecsapp.RecentFragment
import com.example.techspecsapp.SearchFragment

class TabsAdapter(fragmentActivity: FragmentActivity, private val repository: Repository) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> SearchFragment(repository)
        else -> RecentFragment(repository)
    }
}