package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExploreActivity : AppCompatActivity() {
    data class ExplorePost(val image: Int, val isVideo: Boolean, val videoResId: Int? = null)

    class ExploreAdapter(
        private val posts: List<ExplorePost>
    ) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image: ImageView = view.findViewById(R.id.imgPost)
            val play: ImageView = view.findViewById(R.id.imgPlay)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_explore, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val post = posts[position]

            holder.image.setImageResource(post.image)

            if (post.isVideo) {
                holder.play.visibility = View.VISIBLE
            } else {
                holder.play.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {
                if (post.isVideo) {
                    val intent = Intent(holder.itemView.context, ReelsActivity::class.java)
                    intent.putExtra("videoResId", post.videoResId)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
        override fun getItemCount(): Int = posts.size
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        recyclerView = findViewById(R.id.recyclerExplore)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        val posts = listOf(
            ExplorePost(R.drawable.campus11, false),
            ExplorePost(R.drawable.campus21, false),
            ExplorePost(R.drawable.campus3, true, R.raw.campus_reel1),
            ExplorePost(R.drawable.event1, false),
            ExplorePost(R.drawable.student11, true, R.raw.graduation),
            ExplorePost(R.drawable.student2, false),
            ExplorePost(R.drawable.society1, false),
            ExplorePost(R.drawable.university1, true,R.raw.sports_day),
            ExplorePost(R.drawable.campaign1, false)
        )

        recyclerView.adapter = ExploreAdapter(posts)

        findViewById<Button>(R.id.btnTrending).setOnClickListener {
            startActivity(Intent(this, TrendingActivity::class.java))
        }

        findViewById<Button>(R.id.btnUniversities).setOnClickListener {
            startActivity(Intent(this, UniversitiesActivity::class.java))
        }

        findViewById<Button>(R.id.btnEvents).setOnClickListener {
            // Note: EventsActivity needs to be created
            // startActivity(Intent(this, EventsActivity::class.java))
        }

        findViewById<Button>(R.id.btnSocieties).setOnClickListener {
            startActivity(Intent(this, SocietiesActivity::class.java))
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.selectedItemId = R.id.explore

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.explore -> true
                R.id.reels -> {
                    startActivity(Intent(this, ReelsActivity::class.java))
                    true
                }
                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
