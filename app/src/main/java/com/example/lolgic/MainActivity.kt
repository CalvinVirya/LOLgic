package com.example.lolgic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnUsername: Button
    lateinit var etUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnUsername = findViewById(R.id.btnUsername)
        etUsername = findViewById(R.id.etUsername)

        btnUsername.setOnClickListener {

            if(etUsername.text.isNullOrBlank()){
                Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
            } else{
                val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("Username", etUsername.text.toString())
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
                var intent = Intent(MainActivity@this, HomePage::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

}