package com.example.lolgic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class CardAgainstHumanity : AppCompatActivity() {

    lateinit var llContentBg: LinearLayout
    lateinit var ivBack: ImageView
    lateinit var tvApi: TextView
    lateinit var tvOpt1: TextView
    lateinit var tvOpt2: TextView
    lateinit var tvOpt3: TextView
    lateinit var tvOpt4: TextView
    lateinit var tvOpt5: TextView
    lateinit var progressBar: ProgressBar
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card_against_humanity)

        llContentBg = findViewById(R.id.llContentBg)
        ivBack = findViewById(R.id.ivBack)
        tvApi = findViewById(R.id.tvApi)
        progressBar = findViewById(R.id.progressBar)

        progressBar.indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))

        val views = listOf(
            findViewById<TextView>(R.id.tvOpt1),
            findViewById<TextView>(R.id.tvOpt2),
            findViewById<TextView>(R.id.tvOpt3),
            findViewById<TextView>(R.id.tvOpt4),
            findViewById<TextView>(R.id.tvOpt5),
        )

        views.forEach { view ->
            view.setOnClickListener {
                fetchCard(views)
                val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
                val currentPoints = sharedPreferences.getInt("userPoints", 0)
                val updatedPoints = currentPoints + 1

                val editor = sharedPreferences.edit()
                editor.putInt("userPoints", updatedPoints)
                editor.apply()
            }
        }

        ivBack.setOnClickListener{
            finish()
        }

        val drawable = ContextCompat.getDrawable(this, R.drawable.ll_bg)?.mutate()
        drawable?.setTint(Color.parseColor("#000000"))
        llContentBg.background = drawable

        fetchCard(views)
    }

    fun fetchCard(views: List<TextView>){

        requestQueue = Volley.newRequestQueue(this)

        val url = "https://restagainsthumanity.com/api/v1?packs=CAH Base Set"
        progressBar.visibility = View.VISIBLE

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                progressBar.visibility = View.GONE
                val blackArr = response.getJSONArray("black")
                val whiteArr = response.getJSONArray("white")

                // Random 1 black card
                val randomBlack = blackArr.getJSONObject((0 until blackArr.length()).random()).getString("text")
                val randomWhites = (0 until whiteArr.length()).shuffled().take(5).map { whiteArr.getString(it) }

                tvApi.text = randomBlack
                views.zip(randomWhites).forEach { (view, text) ->
                    view.text = text
                }
            },
            { error -> error.printStackTrace() }
        )

        requestQueue.add(request)
    }
}