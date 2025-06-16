package com.example.lolgic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomePage : AppCompatActivity() {

    lateinit var tvUsername: TextView
    lateinit var tvStreak: TextView
    lateinit var tvPoints: TextView
    lateinit var llCardAgainstHumanity: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        tvUsername = findViewById(R.id.tvUsername)
        tvStreak = findViewById(R.id.tvStreak)
        tvPoints = findViewById(R.id.tvPoints)
        llCardAgainstHumanity = findViewById(R.id.llCardAgainstHumanity)

        handleDailyLogin()

        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val username = sharedPreferences.getString("Username", "Guest")
        var point = sharedPreferences.getInt("userPoints", 0)
        tvUsername.text = username
        tvPoints.text = point.toString()

        llCardAgainstHumanity.setOnClickListener{
            var intent = Intent(HomePage@this, CardAgainstHumanity::class.java)
            startActivity(intent)
        }

        val views = listOf(
            findViewById<LinearLayout>(R.id.llRandomJokes),
            findViewById<LinearLayout>(R.id.llCatFacts),
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

    private fun handleDailyLogin() {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val todayDate = Calendar.getInstance()
        val todayString = dateFormat.format(todayDate.time)

        val lastLogin = sharedPreferences.getString("lastLoginDate", "")
        val lastStreak = sharedPreferences.getInt("streakCount", 0)
        var newStreak = 1

        if (lastLogin != todayString) {
            if (!lastLogin.isNullOrEmpty()) {
                val lastLoginDate = Calendar.getInstance().apply {
                    time = dateFormat.parse(lastLogin)!!
                }

                // Add 1 day to the last login date
                lastLoginDate.add(Calendar.DATE, 1)
                val expectedDate = dateFormat.format(lastLoginDate.time)

                newStreak = if (todayString == expectedDate) {
                    lastStreak + 1
                } else {
                    1
                }
            }

            // First login ever or update streak
            editor.putInt("streakCount", newStreak)
            editor.putString("lastLoginDate", todayString)
            editor.apply()

        } else {
            newStreak = lastStreak
        }

        // Set the streak text
        tvStreak.text = "$newStreak"
    }

    override fun onResume() {
        super.onResume()
        updateUserUI()
    }


    private fun updateUserUI() {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val username = sharedPreferences.getString("Username", "Guest")
        val point = sharedPreferences.getInt("userPoints", 0)

        tvUsername.text = username
        tvPoints.text = point.toString()
    }


}