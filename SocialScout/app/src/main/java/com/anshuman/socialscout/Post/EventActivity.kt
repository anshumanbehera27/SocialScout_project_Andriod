package com.anshuman.socialscout.Post

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anshuman.socialscout.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class EventActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmIntent: PendingIntent


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val sharedPreferences = getSharedPreferences("event_prefs", Context.MODE_PRIVATE)
        val pickTimeButton: Button = findViewById(R.id.pick_time_button)
        val previewPickedTimeTextView: TextView = findViewById(R.id.preview_picked_time_textView)
        val pickDateButton: Button = findViewById(R.id.pick_date_button)
        val btsetalaram: Button = findViewById(R.id.btsetalaram)
       val show_selected_date : TextView = findViewById(R.id.show_selected_date)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmManagerBroadcast::class.java)
        alarmIntent =  PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        pickTimeButton.setOnClickListener {
            val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
                .setTitleText("SELECT YOUR TIMING")
                .setHour(12)
                .setMinute(10)
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .build()

            materialTimePicker.show(supportFragmentManager, "MainActivity")

            materialTimePicker.addOnPositiveButtonClickListener {
                val pickedHour: Int = materialTimePicker.hour
                val pickedMinute: Int = materialTimePicker.minute

                val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, pickedHour)
                        set(Calendar.MINUTE, pickedMinute)
                    }.time
                )

                previewPickedTimeTextView.text = formattedTime

                // Store selected time in SharedPreferences
                sharedPreferences.edit().putString("selected_time", formattedTime).apply()
            }
        }

        pickDateButton.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.addOnPositiveButtonClickListener { selectedDateInMillis ->
                val selectedDate = Date(selectedDateInMillis)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate)

                // Display selected date in TextView
                show_selected_date.text = formattedDate

                // Store selected date in SharedPreferences
                sharedPreferences.edit().putLong("selected_date", selectedDateInMillis).apply()
            }

            datePicker.show(supportFragmentManager, "Date Picker")
        }

        // Setup Alarm based on stored date and time
        btsetalaram.setOnClickListener {
            setupAlarm()
        }
    }


    @SuppressLint("ScheduleExactAlarm")
    private fun setupAlarm() {
        val sharedPreferences = getSharedPreferences("event_prefs", Context.MODE_PRIVATE)
        val selectedTime = sharedPreferences.getString("selected_time", null)

        if (!selectedTime.isNullOrEmpty()) {
            val timeParts = selectedTime.split(":")
            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, timeParts[0].toInt())
                set(Calendar.MINUTE, timeParts[1].toInt())
                set(Calendar.SECOND, 0)
            }

            val intent = Intent(this, AlarmManagerBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }

            Toast.makeText(this, "Alarm set for $selectedTime", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please select a time first", Toast.LENGTH_SHORT).show()
        }
    }

}


