package com.example.lolgic

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ActivityRandom : AppCompatActivity() {

    lateinit var tvApi: TextView
    lateinit var tvTitle: TextView
    lateinit var ivBack: ImageView
    lateinit var llContentBg: LinearLayout
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random)

        tvApi = findViewById(R.id.tvApi)
        tvTitle = findViewById(R.id.tvTitle)
        ivBack = findViewById(R.id.ivBack)
        llContentBg = findViewById(R.id.llContentBg)
        progressBar = findViewById(R.id.progressBar)
        scrollView = findViewById(R.id.scrollView)

        ivBack.setOnClickListener{
            finish()
        }

        tvTitle.text = intent.getStringExtra("ActivityName")

        var url: String

        if(tvTitle.text == "Useless Facts") {
            url = "https://uselessfacts.jsph.pl/api/v2/facts/random?language=en"

            requestQueue = Volley.newRequestQueue(this)
            progressBar.visibility = View.VISIBLE

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    progressBar.visibility = View.GONE
                    tvApi.text = response.getString("text")
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)

            val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
            drawable?.setTint(Color.parseColor("#E6E8FD"))
            llContentBg.background = drawable

            tvApi.setTextColor(Color.parseColor("#7D83BF"))

            progressBar.indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#7D83BF"))

            scrollView.viewTreeObserver.addOnScrollChangedListener {
                val view = scrollView.getChildAt(0)
                val diff = view.bottom - (scrollView.height + scrollView.scrollY)

                if (diff == 0){
                    requestQueue.add(request)
                    scrollView.fullScroll(View.FOCUS_UP)
                }
            }

        } else if(tvTitle.text == "Cat Facts"){
            url = "https://catfact.ninja/fact"

            requestQueue = Volley.newRequestQueue(this)
            progressBar.visibility = View.VISIBLE

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    progressBar.visibility = View.GONE
                    tvApi.text = response.getString("fact")
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)

            val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
            drawable?.setTint(Color.parseColor("#F1FCE5"))
            llContentBg.background = drawable

            tvApi.setTextColor(Color.parseColor("#809E60"))

            progressBar.indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#809E60"))

            scrollView.viewTreeObserver.addOnScrollChangedListener {
                val view = scrollView.getChildAt(0)
                val diff = view.bottom - (scrollView.height + scrollView.scrollY)

                if (diff == 0){
                    requestQueue.add(request)
                    scrollView.fullScroll(View.FOCUS_UP)
                }
            }

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

            val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
            drawable?.setTint(Color.parseColor("#E7F2F8"))
            llContentBg.background = drawable

            tvApi.setTextColor(Color.parseColor("#509AC2"))

        } else if(tvTitle.text == "Random Jokes"){
            url = "https://v2.jokeapi.dev/joke/Any?type=single"

            requestQueue = Volley.newRequestQueue(this)
            progressBar.visibility = View.VISIBLE


            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    progressBar.visibility = View.GONE
                    tvApi.text = response.getString("joke") // kalo mau ambil two part nanti disini ambil variable doang nanti baru di assign
                },
                { error ->
                    tvApi.text = "API not found"
                }
            )
            requestQueue.add(request)

            val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
            drawable?.setTint(Color.parseColor("#FAE4ED"))
            llContentBg.background = drawable

            tvApi.setTextColor(Color.parseColor("#BF6F90"))

            progressBar.indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#BF6F90"))

            scrollView.viewTreeObserver.addOnScrollChangedListener {
                val view = scrollView.getChildAt(0)
                val diff = view.bottom - (scrollView.height + scrollView.scrollY)

                if (diff == 0){
                    requestQueue.add(request)
                    scrollView.fullScroll(View.FOCUS_UP)
                }
            }

        } else if(tvTitle.text == "Today in History"){

            url = "https://history.muffinlabs.com/date"

            fetchToday(url)

            val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
            drawable?.setTint(Color.parseColor("#FEF7CF"))
            llContentBg.background = drawable

            tvApi.setTextColor(Color.parseColor("#8C7700"))

            progressBar.indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#8C7700"))

            scrollView.viewTreeObserver.addOnScrollChangedListener {
                val view = scrollView.getChildAt(0)
                val diff = view.bottom - (scrollView.height + scrollView.scrollY)

                if (diff == 0){
                    fetchToday(url)
                    scrollView.fullScroll(View.FOCUS_UP)
                }
            }

        } else {
            Toast.makeText(this, "No API available for: ${tvApi.text}", Toast.LENGTH_SHORT).show()
            return
        }

    }

    fun fetchToday(url: String){
        requestQueue = Volley.newRequestQueue(this)
        progressBar.visibility = View.VISIBLE

        var request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                progressBar.visibility = View.GONE
                // Navigate to "data" -> "Events" array
                var data = response.getJSONObject("data")
                var events = data.getJSONArray("Events")

                // Pick one random event
                var randomIndex = (0 until events.length()).random()
                var event = events.getJSONObject(randomIndex)

                var year = event.getString("year")
                var text = event.getString("text")

                tvApi.text = "Year: ${year}\nEvent: ${text}"

                // Optional: show in UI (e.g. TextView)
                // textView.text = "$year: $text"
            },
            { error ->
                tvApi.text = "API not found"
            }
        )
        requestQueue.add(request)
    }

}