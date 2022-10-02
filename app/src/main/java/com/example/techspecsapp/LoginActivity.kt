package com.example.techspecsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "Login Info",
            Context.MODE_PRIVATE
        )
    }
    private val editor: SharedPreferences.Editor by lazy { preferences.edit() }
    private val etUsername: TextInputEditText by lazy { findViewById(R.id.et_username) }
    private val etPassword: TextInputEditText by lazy { findViewById(R.id.et_password) }
    private val tvError: TextView by lazy { findViewById(R.id.tv_error) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.bt_login).setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (login(username, password)) {
                tvError.visibility = TextView.INVISIBLE
                startActivity(Intent(this,MainActivity::class.java))
            } else {
                tvError.visibility = TextView.VISIBLE
            }
        }

        findViewById<Button>(R.id.bt_register).setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (register(username, password)) {
                tvError.visibility = TextView.INVISIBLE
            } else {
                tvError.visibility = TextView.VISIBLE
            }
        }
    }

    private fun register(username: String, password: String): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            tvError.text = getString(R.string.error_field_empty)
        } else if(!username.isValidUsername()){
            tvError.text=
                getString(R.string.error_invalid_username)
        } else if(preferences.getString(username,null)!=null) {
            tvError.text = getString(R.string.error_duplicate_username)
        }else if(!password.isValidPassword()){
            tvError.text=getString(R.string.error_invalid_password)
        }else{
            editor.putString(username, password).apply()
            Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun login(username: String, password: String): Boolean {
        val passwordInPrefs = preferences.getString(username, null)
        if (username.isEmpty() || password.isEmpty()) {
            tvError.text = getString(R.string.error_field_empty)
        } else if (passwordInPrefs == null) {
            tvError.text = getString(R.string.error_user_DNE)
        } else if (passwordInPrefs == password) {
            return true
        } else {
            tvError.text = getString(R.string.error_wrong_password)
        }
        return false
    }

}