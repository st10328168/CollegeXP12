package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        // Get views
        val imgEventPoster = findViewById<ImageView>(R.id.imgEventPoster)
        val txtEventUniversity = findViewById<TextView>(R.id.txtEventUniversity)
        val txtEventName = findViewById<TextView>(R.id.txtEventName)
        val txtEventTime = findViewById<TextView>(R.id.txtEventTime)

        val btnBuyTicket = findViewById<Button>(R.id.btnBuyTicket)
        val btnRSVP = findViewById<Button>(R.id.btnRSVP)

        // Get event information from TrendingAdapter
        val eventName = intent.getStringExtra("eventName")
        val university = intent.getStringExtra("university")
        val time = intent.getStringExtra("time")

        val poster = intent.getIntExtra(
            "poster",
            R.drawable.event1
        )

        // Display event information
        txtEventName.text = eventName ?: "Event"
        txtEventUniversity.text = university ?: "University"
        txtEventTime.text = time ?: "Time not available"

        imgEventPoster.setImageResource(poster)

        // BUY TICKET
        btnBuyTicket.setOnClickListener {

            val cartIntent = Intent(
                this,
                CartActivity::class.java
            )

            cartIntent.putExtra("eventName", eventName)
            cartIntent.putExtra("university", university)
            cartIntent.putExtra("time", time)
            cartIntent.putExtra("poster", poster)

            startActivity(cartIntent)
        }

        // RSVP
        btnRSVP.setOnClickListener {

            // We will connect this to the RSVP system next.
            btnRSVP.text = "RSVP CONFIRMED"
            btnRSVP.isEnabled = false
        }
    }
}