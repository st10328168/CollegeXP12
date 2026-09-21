package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var btnEditProfile: TextView
    private lateinit var btnSettings: TextView
    private lateinit var btnCart: TextView
    private lateinit var btnRewards: TextView
    private lateinit var btnAchievements: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Connect XML views
        tvName = findViewById(R.id.tvName)
        tvUsername = findViewById(R.id.tvUsername)

        btnEditProfile = findViewById(R.id.btnEditProfile)
        btnSettings = findViewById(R.id.btnSettings)
        btnCart = findViewById(R.id.btnCart)
        btnRewards = findViewById(R.id.btnRewards)
        btnAchievements = findViewById(R.id.btnAchievements)

        // Load connected learner information
        loadLearnerInformation()

        // Edit Profile
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        // Settings
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // CENTRAL CART
        // Every cart button in College XP should eventually
        // point to this same CartActivity.
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Rewards
        btnRewards.setOnClickListener {
            val intent = Intent(this, RewardsActivity::class.java)
            startActivity(intent)
        }

        // Achievements
        btnAchievements.setOnClickListener {
            val intent = Intent(this, AchievementsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadLearnerInformation() {

        val sharedPreferences =
            getSharedPreferences("CollegeXP_User", MODE_PRIVATE)

        val name = sharedPreferences.getString(
            "user_name",
            "Your Name"
        )

        val username = sharedPreferences.getString(
            "username",
            "@username"
        )

        tvName.text = name
        tvUsername.text = username

        // These also change automatically when the
        // connected learner changes.
        btnEditProfile.text = "✎  Edit Profile  ($name)"
        btnSettings.text = "⚙  Settings  ($name)"
    }

    override fun onResume() {
        super.onResume()

        // Refresh name whenever the user comes back
        // from Edit Profile.
        loadLearnerInformation()
    }
}