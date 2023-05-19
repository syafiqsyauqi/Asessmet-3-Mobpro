package org.d3if0157.asessment2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartTrackingActivity : AppCompatActivity() {

    private lateinit var editTextActivity: EditText
    private lateinit var editTextDuration: EditText
    private lateinit var buttonStart: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_tracking_activity)

        editTextActivity = findViewById(R.id.editTextActivity)
        editTextDuration = findViewById(R.id.editTextDuration)
        buttonStart = findViewById(R.id.buttonStart)
        buttonBack = findViewById(R.id.buttonBack)

        buttonStart.setOnClickListener {
            val activity = editTextActivity.text.toString().trim()
            val duration = editTextDuration.text.toString().trim()

            if (activity.isEmpty() || duration.isEmpty()) {
                Toast.makeText(this, "Please enter activity and duration", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Start tracking activity: $activity, duration: $duration minutes", Toast.LENGTH_SHORT).show()
            }
        }

        buttonBack.setOnClickListener {
            onBackPressed()
        }
    }
}
