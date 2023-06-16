package org.d3if0157.asessment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.d3if0157.asessment2.databinding.HistoryActivityBinding
import org.d3if0157.asessment2.model.FitnessActivity

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: HistoryActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data aktivitas dari intent
        val fitnessActivity = intent.getSerializableExtra("fitnessActivity") as? FitnessActivity

        // Menampilkan data aktivitas
        fitnessActivity?.let {
            binding.textActivity.text = "${it.activity}"
            binding.textDuration.text = " ${it.duration}"
            binding.textCaloriesBurned.text = " ${it.caloriesBurned}"
        }
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

}
