package com.example.lolgic

import android.os.Bundle
import android.widget.TextView
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
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random)

        tvApi = findViewById(R.id.tvApi)

        tvApi.text = intent.getStringExtra("ActivityName")

//        requestQueue = Volley.newRequestQueue(this)
//
//        val url = "https://uselessfacts.jsph.pl/api/v2/facts/random?language=en"
//
//        var request = JsonObjectRequest(
//            Request.Method.GET, url, null,
//            { response ->
//                tvApi.text = response.getString("text")
//            },
//            { error ->
//                error.printStackTrace()
//            }
//        )
//        requestQueue.add(request)

    }
}