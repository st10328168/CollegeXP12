package com.example.collegexp

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UniversitiesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: UniversityAdapter

    private val universities = listOf(
        University(
            "University of Johannesburg",
            "Johannesburg, Gauteng",
            "Explore programmes, campus life and opportunities at UJ.",
            R.drawable.campus2
        ),

        University(
            "Wits University",
            "Johannesburg, Gauteng",
            "A leading university offering programmes across multiple faculties.",
            R.drawable.campus3
        ),

        University(
            "Rosebank International University",
            "Johannesburg, Gauteng",
            "Discover courses, applications and student opportunities.",
            R.drawable.campus1
        ),

        University(
            "University of Pretoria",
            "Pretoria, Gauteng",
            "Discover academic programmes, student life and campus events.",
            R.drawable.campus1
        ),

        University(
            "University of Cape Town",
            "Cape Town, Western Cape",
            "Explore one of South Africa's leading research universities.",
            R.drawable.campus2
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university)

        recyclerView = findViewById(R.id.universitiesRecycler)
        searchBar = findViewById(R.id.universitySearch)

        adapter = UniversityAdapter(universities)

        recyclerView.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(this, 2)

        searchBar.addTextChangedListener(
            object : android.text.TextWatcher {

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
                    s: android.text.Editable?
                ) {
                }
            }
        )
    }
}