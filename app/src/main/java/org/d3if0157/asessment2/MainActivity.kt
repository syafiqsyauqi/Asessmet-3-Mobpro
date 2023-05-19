package org.d3if0157.asessment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStartTracking: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartTracking = findViewById(R.id.buttonStartTracking)

        buttonStartTracking.setOnClickListener {
            val intent = Intent(this, StartTrackingActivity::class.java)
            startActivity(intent)
        }
    }
}
