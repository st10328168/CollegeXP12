package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.btnEditProfile).setOnClickListener {

            val preferences = getSharedPreferences(
                "CollegeXPProfile",
                MODE_PRIVATE
            )

            val studentId = preferences.getLong(
                "student_id",
                -1L
            )

            if (studentId == -1L) {

                Toast.makeText(
                    this,
                    "Student profile not found.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                this,
                EditProfileActivity::class.java
            )

            intent.putExtra(
                "student_id",
                studentId
            )

            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SettingsActivity::class.java
                )
            )
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {

            auth.signOut()

            getSharedPreferences(
                "CollegeXPProfile",
                MODE_PRIVATE
            )
                .edit()
                .clear()
                .apply()

            Toast.makeText(
                this,
                "Logged out successfully",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}