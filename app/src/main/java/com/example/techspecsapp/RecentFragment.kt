package com.example.techspecsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.techspecsapp.data.RecentRVAdapter
import com.example.techspecsapp.data.Repository
import com.example.techspecsapp.data.SearchProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecentFragment(val repository: Repository) : Fragment() {
    val adapter = RecentRVAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recent, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_recent)

        recyclerView.adapter = adapter

        return view
    }

    override fun onStart() {
        var list: List<SearchProduct>
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                list = repository.getAllResponsesFromDB()
            }
            adapter.updateData(list)
        }
        super.onStart()
    }


}