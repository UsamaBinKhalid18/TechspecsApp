package com.example.techspecsapp

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.techspecsapp.data.*
import com.example.techspecsapp.data.database.BookmarkDataBase
import com.example.techspecsapp.data.database.ProductDatabase
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivityBookMark : AppCompatActivity() {
    private val repository by lazy {
        Repository.getInstance(
            ProductDatabase.getInstance(
                applicationContext
            ),
            BookmarkDataBase.getInstance(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.tlb_detail_activity))

        val jsonString = intent.getStringExtra("SearchProduct")
        val item = Gson().fromJson(jsonString, SearchProduct::class.java)
        var data: ProductDetail
        val expandableListView: ExpandableListView = findViewById(R.id.expandable_list_view)
        findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).title = item.model.value
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                data = repository.productDetailOffline(item._meta.id)
            }
            val map = ExpandableListData.getData(data)
            val adapter = ExpandableListAdapter(baseContext, map)
            expandableListView.setAdapter(adapter)
            Glide.with(this@DetailActivityBookMark).load(item.image_front.value)
                .into(findViewById(R.id.iv_product))
        }
        findViewById<Button>(R.id.bt_bookmark).visibility=Button.INVISIBLE
    }
}