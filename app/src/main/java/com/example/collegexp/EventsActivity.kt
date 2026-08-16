package com.example.collegexp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: EventAdapter

    private val events = listOf(

        Event(
            "Student Welcome Day",
            "Orientation",
            "Meet new students, explore the campus and learn more about student life.",
            "University of Johannesburg",
            "28 August 2026",
            "09:00",
            "APK Campus",
            R.drawable.campus1
        ),

        Event(
            "Tech & Innovation Meetup",
            "Technology",
            "Discover new technology trends and connect with students interested in innovation.",
            "Wits University",
            "30 August 2026",
            "14:00",
            "Wits Main Campus",
            R.drawable.campus13
        ),

        Event(
            "Entrepreneurship Workshop",
            "Business",
            "Learn how to develop your business idea and turn it into a real opportunity.",
            "University of Pretoria",
            "2 September 2026",
            "10:00",
            "Hatfield Campus",
            R.drawable.campus12
        ),

        Event(
            "Campus Sports Day",
            "Sports",
            "Enjoy a day of football, athletics, games and other sporting activities.",
            "University of Johannesburg",
            "5 September 2026",
            "08:00",
            "UJ Soweto Campus",
            R.drawable.campus15
        ),

        Event(
            "Student Career Fair",
            "Career",
            "Meet employers, explore career opportunities and learn about graduate programmes.",
            "University of Cape Town",
            "10 September 2026",
            "09:00",
            "Upper Campus",
            R.drawable.campus14
        ),

        Event(
            "Music & Arts Festival",
            "Arts & Culture",
            "Experience live performances, music, art and student creativity.",
            "Wits University",
            "15 September 2026",
            "17:00",
            "Wits Amphitheatre",
            R.drawable.campus3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_events)

        recyclerView = findViewById(R.id.eventsRecycler)
        searchBar = findViewById(R.id.eventSearch)

        adapter = EventAdapter(events)

        recyclerView.layoutManager =
            GridLayoutManager(this, 2)

        recyclerView.adapter = adapter

        searchBar.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    adapter.filter(s.toString())
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )
    }
}