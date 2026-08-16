package com.example.collegexp

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class ReelsActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reels)

        videoView = findViewById(R.id.videoView)

        val videoUri = "android.resource://$packageName/${R.raw.intro}".toUri()

        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            videoView.start()
            val likeButton = findViewById<ImageButton>(R.id.btnLike)
            val commentButton = findViewById<ImageButton>(R.id.btnComment)
            val shareButton = findViewById<ImageButton>(R.id.btnShare)
            val likesText = findViewById<TextView>(R.id.txtLikes)

            var liked = false
            var likeCount = 0

            likeButton.setOnClickListener {

                if (!liked) {
                    liked = true
                    likeCount++
                    likeButton.setImageResource(android.R.drawable.btn_star_big_on)
                } else {
                    liked = false
                    likeCount--
                    likeButton.setImageResource(android.R.drawable.btn_star_big_off)
                }

                likesText.text = likeCount.toString()
            }

            commentButton.setOnClickListener {
                Toast.makeText(this, "Comments coming soon!", Toast.LENGTH_SHORT).show()
            }

            shareButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Check out this reel on College XP!")
                startActivity(Intent.createChooser(intent, "Share Reel"))
            }

            val exploreButton = findViewById<TextView>(R.id.btnExplore)
            exploreButton.setOnClickListener {
                startActivity(Intent(this, ExploreActivity::class.java))
                val videoResId = intent.getIntExtra("videoResId", 0)

                if (videoResId != 0) {
                    val uri = Uri.parse("android.resource://$packageName/$videoResId")
                    videoView.setVideoURI(uri)
                    videoView.start()
                }
            }

        }
    }
}