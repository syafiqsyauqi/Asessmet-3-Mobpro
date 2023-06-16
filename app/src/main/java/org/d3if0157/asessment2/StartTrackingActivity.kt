package org.d3if0157.asessment2

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.d3if0157.asessment2.databinding.StartTrackingActivityBinding
import org.d3if0157.asessment2.model.FitnessActivity
import java.util.concurrent.TimeUnit

class StartTrackingActivity : AppCompatActivity() {

    private lateinit var binding: StartTrackingActivityBinding
    private lateinit var fitnessTracker: FitnessTracker
    private lateinit var fitnessActivity: FitnessActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartTrackingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fitnessTracker = FitnessTracker(this)

        binding.buttonStart.setOnClickListener {
            val activity = binding.editTextActivity.text.toString().trim()
            val duration = binding.editTextDuration.text.toString().toIntOrNull()

            if (activity.isNotEmpty() && duration != null && duration > 0) {
                val caloriesBurned = calculateCaloriesBurned(activity, duration)
                fitnessActivity = FitnessActivity(activity, duration, caloriesBurned)
                fitnessTracker.trackActivity(fitnessActivity)

                // Menampilkan hasil perhitungan dan inputan pengguna
                binding.textCaloriesBurned.text = "Calories Burned: $caloriesBurned"
                binding.textActivity.text = "Activity: $activity"
                binding.textDuration.text = "Duration: $duration minutes"

                Toast.makeText(
                    this,
                    "Start tracking activity: $activity, duration: $duration minutes",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Please enter valid activity and duration", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonHistory.setOnClickListener {
            if (::fitnessActivity.isInitialized) {
                // Mengirim data aktivitas ke HistoryActivity
                val intent = Intent(this, HistoryActivity::class.java)
                intent.putExtra("fitnessActivity", fitnessActivity)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please start tracking an activity first", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun calculateCaloriesBurned(activity: String, duration: Int): Double {
        // Menggunakan contoh nilai kalori yang tetap untuk setiap aktivitas
        return when (activity) {
            "Swimming" -> duration * 10.0 // Misalnya, menganggap bahwa berenang membakar 10 kalori per menit
            "Running" -> duration * 8.0 // Misalnya, menganggap bahwa berlari membakar 8 kalori per menit
            else -> 0.0
        }
    }
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}