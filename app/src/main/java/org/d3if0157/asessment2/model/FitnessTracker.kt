package org.d3if0157.asessment2
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.d3if0157.asessment2.model.FitnessActivity
import android.Manifest
import android.app.Activity

class FitnessTracker(private val activity: Activity) {
    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences(
        "FitnessTrackerPrefs", Context.MODE_PRIVATE
    )


    fun trackActivity(activity: FitnessActivity) {
        val activities = getTrackedActivities().toMutableList()
        activities.add(activity)
        saveTrackedActivities(activities)
    }

    fun getTrackedActivities(): List<FitnessActivity> {
        val activitiesJson = sharedPreferences.getString("activities", "[]")
        return Gson().fromJson(activitiesJson, object : TypeToken<List<FitnessActivity>>() {}.type)
    }

    private fun saveTrackedActivities(activities: List<FitnessActivity>) {
        val activitiesJson = Gson().toJson(activities)
        sharedPreferences.edit().putString("activities", activitiesJson).apply()
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }

}
