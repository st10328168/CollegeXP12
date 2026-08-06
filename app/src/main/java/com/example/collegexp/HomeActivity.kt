package com.example.collegexp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPosts)

        val posts = listOf(
            Post(
                R.drawable.campus1,
                "Rosebank International University",
                "Applications are now open!",
                120,
                20
            ),
            Post(
                R.drawable.campus2,
                "University of Johannesburg",
                "Visit our Open Day this Saturday!",
                90,
                15
            ),
            Post(
                R.drawable.campus3,
                "Wits University",
                "Engineering applications are now open.",
                200,
                35
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PostAdapter(posts)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    true
                }

                R.id.reels -> {
                    startActivity(Intent(this, ReelsActivity::class.java))
                    true
                }

                R.id.profile -> {
                    // TODO: Open ProfileActivity
                    true
                }

                R.id.explore -> {
                   startActivity(Intent(this, ExploreActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}