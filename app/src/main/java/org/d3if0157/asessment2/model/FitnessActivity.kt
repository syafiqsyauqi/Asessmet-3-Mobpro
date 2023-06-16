package org.d3if0157.asessment2.model

import java.io.Serializable

data class FitnessActivity(
    val activity:String,
    val duration: Int,
    val caloriesBurned: Double
) : Serializable
