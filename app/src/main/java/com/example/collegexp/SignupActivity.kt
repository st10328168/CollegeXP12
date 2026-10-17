package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.collegexp.api.RetrofitClient
import com.example.collegexp.model.Student
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val firstName = findViewById<EditText>(R.id.firstNameSignup)
        val lastName = findViewById<EditText>(R.id.lastNameSignup)
        val phone = findViewById<EditText>(R.id.phoneSignup)
        val email = findViewById<EditText>(R.id.emailSignup)
        val password = findViewById<EditText>(R.id.passwordSignup)
        val signupBtn = findViewById<Button>(R.id.signupBtn)

        signupBtn.setOnClickListener {

            val firstNameText = firstName.text.toString().trim()
            val lastNameText = lastName.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (
                firstNameText.isEmpty() ||
                lastNameText.isEmpty() ||
                phoneText.isEmpty() ||
                emailText.isEmpty() ||
                passwordText.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Please fill in all fields.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (passwordText.length < 6) {
                Toast.makeText(
                    this,
                    "Password must be at least 6 characters.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            signupBtn.isEnabled = false

            // Create Firebase account
            auth.createUserWithEmailAndPassword(
                emailText,
                passwordText
            ).addOnCompleteListener { firebaseTask ->

                if (firebaseTask.isSuccessful) {

                    // Firebase account created.
                    // Now create the Student in Spring Boot.
                    val student = Student(
                        firstName = firstNameText,
                        lastName = lastNameText,
                        email = emailText,
                        phoneNumber = phoneText
                    )

                    lifecycleScope.launch {

                        try {

                            val response =
                                RetrofitClient.studentApi.createStudent(student)

                            if (response.isSuccessful && response.body() != null) {

                                val createdStudent = response.body()!!

                                // Store the Spring Boot student ID
                                val preferences = getSharedPreferences(
                                    "CollegeXPProfile",
                                    MODE_PRIVATE
                                )

                                preferences.edit()
                                    .putLong(
                                        "student_id",
                                        createdStudent.id ?: -1L
                                    )
                                    .putString(
                                        "firstName",
                                        createdStudent.firstName
                                    )
                                    .putString(
                                        "lastName",
                                        createdStudent.lastName
                                    )
                                    .putString(
                                        "email",
                                        createdStudent.email
                                    )
                                    .putString(
                                        "phoneNumber",
                                        createdStudent.phoneNumber ?: ""
                                    )
                                    .apply()

                                Toast.makeText(
                                    this@SignupActivity,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                startActivity(
                                    Intent(
                                        this@SignupActivity,
                                        HomeActivity::class.java
                                    )
                                )

                                finish()

                            } else {

                                // Student creation failed.
                                // Remove the Firebase account so we don't
                                // leave an incomplete account behind.
                                auth.currentUser?.delete()

                                Toast.makeText(
                                    this@SignupActivity,
                                    "Account could not be created in College XP.",
                                    Toast.LENGTH_LONG
                                ).show()

                                signupBtn.isEnabled = true
                            }

                        } catch (e: Exception) {

                            // API connection failed.
                            auth.currentUser?.delete()

                            Toast.makeText(
                                this@SignupActivity,
                                "Could not connect to College XP API.",
                                Toast.LENGTH_LONG
                            ).show()

                            signupBtn.isEnabled = true
                        }
                    }

                } else {

                    signupBtn.isEnabled = true

                    Toast.makeText(
                        this,
                        firebaseTask.exception?.message
                            ?: "Signup failed.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}