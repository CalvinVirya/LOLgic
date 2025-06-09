package com.example.lolgic

import android.os.Bundle
import android.util.Log
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

        } else if(tvTitle.text == "Cat Facts"){
            url = "https://catfact.ninja/fact"

            requestQueue = Volley.newRequestQueue(this)

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    tvApi.text = response.getString("fact")
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)
        } else if(tvTitle.text == "Card Against Humanity"){
            tvApi.text = "tes"

            requestQueue = Volley.newRequestQueue(this)

            val url = "https://restagainsthumanity.com/api/v1?packs=CAH Base Set"

            val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val blackArr = response.getJSONArray("black")
                    val whiteArr = response.getJSONArray("white")

                    // Random 1 black card
                    val randomBlack = blackArr.getString((0 until blackArr.length()).random())

                    // Random 5 white cards
                    val whiteIndexes = mutableSetOf<Int>()
                    while (whiteIndexes.size < 5) {
                        whiteIndexes.add((0 until whiteArr.length()).random())
                    }

                    val whites = whiteIndexes.map { whiteArr.getString(it) }

                    // Display
                    Log.d("CAH", "Black: $randomBlack")
                    whites.forEachIndexed { index, card ->
                        Log.d("CAH", "White ${index + 1}: $card")
                    }

                },
                { error -> error.printStackTrace() }
            )


            requestQueue.add(request)
        } else if(tvTitle.text == "Random Jokes"){
            url = "https://v2.jokeapi.dev/joke/Any?type=single"

            requestQueue = Volley.newRequestQueue(this)

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    tvApi.text = response.getString("joke") // kalo mau ambil two part nanti disini ambil variable doang nanti baru di assign
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)
        } else if(tvTitle.text == "Today in History"){
            url = "https://history.muffinlabs.com/date"

            requestQueue = Volley.newRequestQueue(this)

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    // Navigate to "data" -> "Events" array
                    val data = response.getJSONObject("data")
                    val events = data.getJSONArray("Events")

                    // Pick one random event
                    val randomIndex = (0 until events.length()).random()
                    val event = events.getJSONObject(randomIndex)

                    val year = event.getString("year")
                    val text = event.getString("text")

                    tvApi.text = "Year: ${year}, Event: ${text}"

                    // Optional: show in UI (e.g. TextView)
                    // textView.text = "$year: $text"
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)
        }

        else {
            Toast.makeText(this, "No API available for: ${tvApi.text}", Toast.LENGTH_SHORT).show()
            return
        }

    }

}