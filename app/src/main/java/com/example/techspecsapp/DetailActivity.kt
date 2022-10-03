package com.example.techspecsapp

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.techspecsapp.data.ExpandableListAdapter
import com.example.techspecsapp.data.ExpandableListData
import com.example.techspecsapp.data.Repository
import com.example.techspecsapp.data.SearchProduct
import com.example.techspecsapp.data.database.ProductDatabase
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private val repository by lazy {
        Repository.getInstance(
            ProductDatabase.getInstance(
                applicationContext
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.tlb_detail_activity))

        val jsonString = intent.getStringExtra("SearchProduct")
        val item = Gson().fromJson(jsonString, SearchProduct::class.java)
        repository.productDetail(item._meta.id)
        val expandableListView: ExpandableListView = findViewById(R.id.expandable_list_view)
        repository.productDetail.observe(this) {
            val map = ExpandableListData.getData(it)
            val adapter = ExpandableListAdapter(baseContext, map)
            expandableListView.setAdapter(adapter)
            lifecycleScope.launch(Dispatchers.IO) {
                repository.insertProduct(item, it)
            }
            Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
        }
        Glide.with(this).load(item.image_front.value).into(findViewById(R.id.iv_product))
        findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).title = item.model.value
    }


}