package com.example.techspecsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.techspecsapp.data.Repository
import com.example.techspecsapp.data.SearchRVAdapter


class SearchFragment(val repository: Repository) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_search)
        val adapter = SearchRVAdapter()
        recyclerView.adapter = adapter

        view.findViewById<android.widget.SearchView>(R.id.search_view)
            .setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(string: String): Boolean {

                    repository.searchProduct(string)
                    repository.productsList.observe(this@SearchFragment.viewLifecycleOwner) {
                        adapter.updateData(it)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })

        return view
    }


}