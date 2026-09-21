package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.collegexp.repository.CourseRepository
import kotlinx.coroutines.launch
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            try {
                val courses = CourseRepository().getCourses()

                Toast.makeText(
                    this@SplashActivity,
                    "API connected! Courses: ${courses.size}",
                    Toast.LENGTH_LONG
                ).show()

            } catch (e: Exception) {
                Toast.makeText(
                    this@SplashActivity,
                    "API connection failed: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)
    }
}
