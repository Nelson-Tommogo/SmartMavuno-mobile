package com.example.smartmavuno.appActivities

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.smartmavuno.R

fun createNotificationChannels(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val generalChannel = NotificationChannel(
            "general_channel_id",
            "General Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "General notifications for the app"
        }

        val systemChannel = NotificationChannel(
            "system_channel_id",
            "System Notifications",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "System notifications for the app"
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(generalChannel)
        notificationManager.createNotificationChannel(systemChannel)
    }
}

fun showGeneralNotification(context: Context, title: String, message: String) {
    val builder = NotificationCompat.Builder(context, "general_channel_id")
        .setSmallIcon(R.drawable.notify)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(1, builder.build())
    }
}

fun showSystemNotification(context: Context, title: String, message: String) {
    val builder = NotificationCompat.Builder(context, "system_channel_id")
        .setSmallIcon(R.drawable.notify)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(2, builder.build())
    }
}
