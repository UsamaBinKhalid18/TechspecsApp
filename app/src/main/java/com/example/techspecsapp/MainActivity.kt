package com.example.techspecsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.techspecsapp.data.Repository
import com.example.techspecsapp.data.SearchRVAdapter

class MainActivity : AppCompatActivity() {
    private val repository=Repository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView=findViewById(R.id.rv_search)
        val adapter=SearchRVAdapter()
        recyclerView.adapter=adapter

        findViewById<android.widget.SearchView>(R.id.search_view).setOnQueryTextListener(object:android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(string: String): Boolean {
                repository.searchProduct(string)
                repository.productsList.observe(this@MainActivity){
                    adapter.updateData(it)
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }
}