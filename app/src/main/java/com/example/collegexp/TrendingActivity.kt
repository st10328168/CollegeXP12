package com.example.collegexp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTrending)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val eventList = listOf(

            TrendingEvent(
                "Rosebank College",
                "Kampus Konnect",
                "Friday • 9:00 AM",
                R.drawable.rosebank_logo,
                R.drawable.kampus_konnect,
                126
            ),

            TrendingEvent(
                "Wits University",
                "Sharing Session",
                "Friday • 4:00 PM",
                R.drawable.wits_logo,
                R.drawable.sharing_session,
                89
            )

        )

        recyclerView.adapter = TrendingAdapter(eventList)
    }
}