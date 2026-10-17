package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Notification setting
        val notificationsSwitch =
            findViewById<Switch>(R.id.switchNotifications)

        val preferences = getSharedPreferences(
            "CollegeXPSettings",
            MODE_PRIVATE
        )

        notificationsSwitch.isChecked =
            preferences.getBoolean("notifications_enabled", true)

        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit()
                .putBoolean("notifications_enabled", isChecked)
                .apply()

            val message = if (isChecked) {
                "Notifications enabled"
            } else {
                "Notifications disabled"
            }

            Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Dark mode setting
        val darkModeSwitch =
            findViewById<Switch>(R.id.switchDarkMode)

        darkModeSwitch.isChecked =
            preferences.getBoolean("dark_mode_enabled", false)

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit()
                .putBoolean("dark_mode_enabled", isChecked)
                .apply()

            Toast.makeText(
                this,
                if (isChecked) "Dark mode enabled"
                else "Dark mode disabled",
                Toast.LENGTH_SHORT
            ).show()
        }

        // About College XP
        val aboutButton =
            findViewById<Button>(R.id.btnAbout)

        aboutButton.setOnClickListener {
            Toast.makeText(
                this,
                "College XP\nVersion 1.0",
                Toast.LENGTH_LONG
            ).show()
        }

        // Logout
        val logoutButton =
            findViewById<Button>(R.id.btnLogout)

        logoutButton.setOnClickListener {
            Toast.makeText(
                this,
                "Logged out of College XP",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}