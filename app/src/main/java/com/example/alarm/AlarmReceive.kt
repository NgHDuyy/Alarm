package com.example.alarm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import java.util.Date


const val notificationID = 1
const val channelID = "channel1"
const val title = "title"
const val status = "status"


class AlarmReceive : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskStackBuilder = TaskStackBuilder.create(context)
        taskStackBuilder.addNextIntentWithParentStack(Intent(context,MainActivity::class.java))
        val pendingIntent = taskStackBuilder.getPendingIntent(Date().time.toInt(), PendingIntent.FLAG_UPDATE_CURRENT)
        val notification: Notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle(intent.getStringExtra(title))
            .setContentIntent(pendingIntent)
            .setSound(null)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)

        val intent1 = Intent(context, AlarmSound::class.java)
        intent1.putExtra(status, intent.getStringExtra(status))
        context.startService(intent1)
    }
}