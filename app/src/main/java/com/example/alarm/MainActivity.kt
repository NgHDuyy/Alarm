package com.example.alarm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.alarm.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainPresenter: MainPresenter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        setLayout()
        mainPresenter.createNotificationChannel()

        binding.setAlarm.setOnClickListener {
            binding.time.text = "${DecimalFormat("00").format(binding.timePicker.hour)}:${
                DecimalFormat("00").format(binding.timePicker.minute)
            }"
            if (binding.checkRepeat.isChecked) {
                mainPresenter.scheduleNotification(binding.time.text.toString(), true)
            } else {
                mainPresenter.scheduleNotification(binding.time.text.toString(), false)
            }
            binding.cancel.visibility = View.VISIBLE
        }

        binding.cancel.setOnClickListener {
            mainPresenter.cancelAlarm()
        }
        binding.reset.setOnClickListener {
            setLayout()
        }
    }


    private fun setLayout() {
        val cal = Calendar.getInstance()

        binding.timePicker.hour = cal.get(Calendar.HOUR_OF_DAY)
        binding.timePicker.minute = cal.get(Calendar.MINUTE)

        binding.time.text = "${DecimalFormat("00").format(binding.timePicker.hour)}:${
            DecimalFormat("00").format(binding.timePicker.minute)
        }"
    }
}