package com.example.techspecsapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.techspecsapp.data.MyReceiver
import com.example.techspecsapp.data.Repository
import com.example.techspecsapp.data.TabsAdapter
import com.example.techspecsapp.data.database.BookmarkDataBase
import com.example.techspecsapp.data.database.ProductDatabase
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_main)
        repository.username= intent.getStringExtra("username")?:""
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        viewPager.adapter = TabsAdapter(this, repository)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Search"
                2->"Bookmark"
                else -> "Recent"
            }
        }.attach()
    }

    override fun onDestroy() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        val delayMillis = 15000
        val timeInMillis: Long = System.currentTimeMillis() + delayMillis
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        finishAffinity()
    }


}