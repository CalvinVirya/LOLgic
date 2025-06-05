package com.example.lolgic

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ActivityRandom : AppCompatActivity() {

    lateinit var tvApi: TextView
    lateinit var tvTitle: TextView
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random)

        tvApi = findViewById(R.id.tvApi)
        tvTitle = findViewById(R.id.tvTitle)

        tvTitle.text = intent.getStringExtra("ActivityName")

        var url: String

        if(tvTitle.text == "Useless Facts") {
            url = "https://uselessfacts.jsph.pl/api/v2/facts/random?language=en"
        } else {
            Toast.makeText(this, "No API available for: ${tvApi.text}", Toast.LENGTH_SHORT).show()
            return
        }



        requestQueue = Volley.newRequestQueue(this)

        var request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                tvApi.text = response.getString("text")
            },
            { error ->
                tvApi.text = "API not found"
            }
        )
        requestQueue.add(request)

    }
}