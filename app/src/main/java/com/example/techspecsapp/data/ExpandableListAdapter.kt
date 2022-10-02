package com.example.techspecsapp.data

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView


class ExpandableListAdapter(private val context: Context,private val listMap:Map<String,List<String>>):BaseExpandableListAdapter() {
    val titleList=listMap.keys.toList()

    override fun getGroupCount(): Int=titleList.count()

    override fun getChildrenCount(p0: Int): Int =listMap[titleList[p0]]!!.count()

    override fun getGroup(p0: Int)=titleList[p0]

    override fun getChild(p0: Int, p1: Int)= listMap[titleList[p0]]!![p1]

    override fun getGroupId(p0: Int): Long =p0.toLong()

    override fun getChildId(p0: Int, p1: Int): Long=p1.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val p=LayoutInflater.from(context).inflate(com.example.techspecsapp.R.layout.list_group,p3,false)
        Log.d("TAG", "getGroupView: $p0 ,$p1")
        val listTitleTextView = p.findViewById<TextView>(com.example.techspecsapp.R.id.listTitle)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = getGroup(p0)
        return p
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, convertView: View?, p4: ViewGroup?): View {
        val p=LayoutInflater.from(context).inflate(com.example.techspecsapp.R.layout.list_item,p4,false)
        Log.d("TAG", "getGroupView: $p0 ,$p1")

        val expandedListTextView = p.findViewById(com.example.techspecsapp.R.id.expandedListItem) as TextView
        expandedListTextView.text = getChild(p0, p1)
        return p
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}