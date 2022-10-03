package com.example.techspecsapp.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.techspecsapp.data.database.ProductDatabase

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Thread {
            ProductDatabase.getInstance(context).searchResponseDao().deleteAll()
        }.start()
    }
}