package com.example.lolgic

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import org.w3c.dom.Text

class HomePage : AppCompatActivity() {

    lateinit var tvUsername: TextView
    lateinit var tvStreak: TextView
    lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        tvUsername = findViewById(R.id.tvUsername)
        tvStreak = findViewById(R.id.tvStreak)
        tvPoints = findViewById(R.id.tvPoints)

        val views = listOf(
            findViewById<LinearLayout>(R.id.llRandomJokes),
            findViewById<LinearLayout>(R.id.llCatFacts),
            findViewById<LinearLayout>(R.id.llCardAgaintsHumanity),
            findViewById<LinearLayout>(R.id.llTodayInHistory),
            findViewById<LinearLayout>(R.id.llUselessFacts),
        )

        views.forEach { layout ->
            layout.setOnClickListener {
                val activityName = (layout.getChildAt(1) as TextView).text.toString()

                val intent = Intent(HomePage@this, ActivityRandom::class.java)
                intent.putExtra("ActivityName", activityName)
                startActivity(intent)
            }
        }

    }
}