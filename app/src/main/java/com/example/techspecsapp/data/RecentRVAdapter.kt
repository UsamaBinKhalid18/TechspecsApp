package com.example.techspecsapp.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techspecsapp.DetailActivityOffline
import com.example.techspecsapp.R
import com.google.gson.Gson

class RecentRVAdapter : RecyclerView.Adapter<RecentRVAdapter.SearchViewHolder>() {
    private var dataset = listOf<SearchProduct>()

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduct = view.findViewById<ImageView>(R.id.iv_search)
        val tvModel = view.findViewById<TextView>(R.id.tv_model)
        val tvBrand = view.findViewById<TextView>(R.id.tv_brand)
        val cvSearch = view.findViewById<CardView>(R.id.cv_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_search_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = dataset[position]
        Glide.with(holder.itemView.context).load(item.image_front.value).into(holder.ivProduct)
        val productName = item.model.value.removePrefix(item.brand.value + " ")
        holder.tvModel.text = holder.itemView.context.getString(R.string.model, productName)
        holder.tvBrand.text = holder.itemView.context.getString(R.string.brand, item.brand.value)
        holder.cvSearch.setOnClickListener {
            val intent = Intent(it.context, DetailActivityOffline::class.java)
            val jsonString = Gson().toJson(item)
            intent.putExtra("SearchProduct", jsonString)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataset.count()

    fun updateData(data: List<SearchProduct>) {
        dataset = data
        notifyDataSetChanged()
    }
}