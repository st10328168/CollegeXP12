package com.example.collegexp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SocietiesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: SocietyAdapter

    private val societies = listOf(

        Society(
            "Computer Science Society",
            "Technology",
            "Connect with students interested in coding, software development and technology.",
            "University of Johannesburg",
            R.drawable.campus1
        ),

        Society(
            "Business Society",
            "Business",
            "A community for students interested in entrepreneurship, business and leadership.",
            "University of Johannesburg",
            R.drawable.campus2
        ),

        Society(
            "Law Society",
            "Law",
            "Engage with fellow law students through discussions, events and academic activities.",
            "Wits University",
            R.drawable.campus3
        ),

        Society(
            "Debate Society",
            "Academic",
            "Develop your public speaking, critical thinking and debating skills.",
            "University of Pretoria",
            R.drawable.campus1
        ),

        Society(
            "Sports Society",
            "Sports",
            "Meet students who enjoy sports, fitness and recreational activities.",
            "University of Cape Town",
            R.drawable.campus2
        ),

        Society(
            "Music Society",
            "Arts & Culture",
            "A creative community for students passionate about music and performance.",
            "Wits University",
            R.drawable.campus3
        ),

        Society(
            "Photography Society",
            "Arts & Culture",
            "Explore photography, creative media and visual storytelling with other students.",
            "University of Pretoria",
            R.drawable.campus1
        ),

        Society(
            "Entrepreneurship Society",
            "Business",
            "Learn about startups, innovation and building your own business.",
            "University of Cape Town",
            R.drawable.campus2
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_societies)

        recyclerView = findViewById(R.id.societiesRecycler)
        searchBar = findViewById(R.id.societySearch)

        adapter = SocietyAdapter(societies)

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