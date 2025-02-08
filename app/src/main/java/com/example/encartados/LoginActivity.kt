package com.example.encartados

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encartados.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDatabaseHelper(this)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val name = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_EMAIL, email)
            put(UserDatabaseHelper.COLUMN_NAME, name)
            put(UserDatabaseHelper.COLUMN_PASSWORD, password)
        }

        val newRowId = db.insert(UserDatabaseHelper.TABLE_USERS, null, values)
        if (newRowId != -1L) {
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val db = dbHelper.readableDatabase
        val cursor = db.query(
            UserDatabaseHelper.TABLE_USERS,
            arrayOf(UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_EMAIL, UserDatabaseHelper.COLUMN_NAME),
            "${UserDatabaseHelper.COLUMN_EMAIL} = ? AND ${UserDatabaseHelper.COLUMN_PASSWORD} = ?",
            arrayOf(email, password),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ID))
            val userName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME))

            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USER_ID", userId)
                putExtra("USER_NAME", userName)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "User not registered", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
    }
}