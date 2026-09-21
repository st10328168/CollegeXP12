package com.example.collegexp
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.collegexp.repository.CourseRepository
import kotlinx.coroutines.launch
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            try {
                val courses = CourseRepository().getCourses()

                Toast.makeText(
                    this@MainActivity,
                    "API connected! Courses: ${courses.size}",
                    Toast.LENGTH_LONG
                ).show()

            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "API connection failed: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val exploreButton = findViewById<Button>(R.id.btnExplore)

        exploreButton.setOnClickListener {
            val intent = Intent(this, ExploreActivity::class.java)
            startActivity(intent)
        }
    }
}