package com.example.lolgic

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class HomePage : AppCompatActivity() {

    lateinit var tvUsername: TextView
    lateinit var tvStreak: TextView
    lateinit var tvPoints: TextView
    lateinit var llRandomJokes: LinearLayout
    lateinit var llUselessFacts: LinearLayout
    lateinit var llCatFacts: LinearLayout
    lateinit var llCardAgaintsHumanity: LinearLayout
    lateinit var llTodayInHistory: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        tvUsername = findViewById(R.id.tvUsername)
        tvStreak = findViewById(R.id.tvStreak)
        tvPoints = findViewById(R.id.tvPoints)
        llRandomJokes = findViewById(R.id.llRandomJokes)
        llUselessFacts = findViewById(R.id.llUselessFacts)
        llCatFacts = findViewById(R.id.llCatFacts)
        llCardAgaintsHumanity = findViewById(R.id.llCardAgaintsHumanity)
        llTodayInHistory = findViewById(R.id.llTodayInHistory)
    }
}