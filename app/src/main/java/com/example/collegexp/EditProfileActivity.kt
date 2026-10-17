package com.example.collegexp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.collegexp.api.RetrofitClient
import com.example.collegexp.model.Student
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var phoneNumber: EditText

    private var studentId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firstName = findViewById(R.id.etFirstName)
        lastName = findViewById(R.id.etLastName)
        email = findViewById(R.id.etEmail)
        phoneNumber = findViewById(R.id.etPhoneNumber)

        val saveButton = findViewById<Button>(R.id.btnSaveProfile)
        val cancelButton = findViewById<Button>(R.id.btnCancel)

        // Get the student ID
        studentId = intent.getLongExtra("student_id", -1L)

        if (studentId == -1L) {
            Toast.makeText(
                this,
                "Student information not found.",
                Toast.LENGTH_LONG
            ).show()

            finish()
            return
        }

        // Load the student's current information
        loadStudent()

        // Save changes
        saveButton.setOnClickListener {
            updateStudent()
        }

        // Cancel
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadStudent() {

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitClient.studentApi.getStudent(studentId)

                if (response.isSuccessful && response.body() != null) {

                    val student = response.body()!!

                    firstName.setText(student.firstName)
                    lastName.setText(student.lastName)
                    email.setText(student.email)
                    phoneNumber.setText(student.phoneNumber ?: "")

                } else {

                    Toast.makeText(
                        this@EditProfileActivity,
                        "Could not load your profile.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@EditProfileActivity,
                    "Connection error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun updateStudent() {

        val firstNameValue = firstName.text.toString().trim()
        val lastNameValue = lastName.text.toString().trim()
        val emailValue = email.text.toString().trim()
        val phoneValue = phoneNumber.text.toString().trim()

        // Validate required fields
        if (firstNameValue.isEmpty()) {
            firstName.error = "First name is required"
            firstName.requestFocus()
            return
        }

        if (lastNameValue.isEmpty()) {
            lastName.error = "Last name is required"
            lastName.requestFocus()
            return
        }

        if (emailValue.isEmpty()) {
            email.error = "Email is required"
            email.requestFocus()
            return
        }

        val updatedStudent = Student(
            id = studentId,
            firstName = firstNameValue,
            lastName = lastNameValue,
            email = emailValue,
            phoneNumber = phoneValue
        )

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitClient.studentApi.updateStudent(
                        studentId,
                        updatedStudent
                    )

                if (response.isSuccessful && response.body() != null) {

                    Toast.makeText(
                        this@EditProfileActivity,
                        "Profile updated successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Return to ProfileActivity
                    finish()

                } else {

                    Toast.makeText(
                        this@EditProfileActivity,
                        "Failed to update profile. Code: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@EditProfileActivity,
                    "Connection error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
