package com.example.azkar.ui.adapter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.azkar.R
import com.example.azkar.ui.MainActivity
import com.example.azkar.ui.MainFragment

class NotificationUtils(context:Context) {
    private var mContext = context
    private lateinit var morningNotification: NotificationCompat.Builder
    private lateinit var nightNotification: NotificationCompat.Builder
    private val notificationManager = NotificationManagerCompat.from(mContext)
    private val CHANNEL_ID = "My_Notification_Channel"

    init {
        createNotificationChannel()
        initNotificationBuilder()
    }

    fun launchNotification(type:Int){
        if (type==1){
        with(NotificationManagerCompat.from(mContext)) {
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(0, morningNotification.build())
        }}
        if (type==2){
            with(NotificationManagerCompat.from(mContext)) {
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, nightNotification.build())
            }}
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Azkar Channel"
            val descriptionText = "Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notifiManager: NotificationManager =
                mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifiManager.createNotificationChannel(channel)
        }
    }

    private fun initNotificationBuilder() {
        val sampleIntent = Intent(mContext,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(mContext, 0, sampleIntent, 0)

        morningNotification = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Morning Azkar")
            .setContentText("Don't forget to read morning azkar")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            // Automatically removes the notification when the user taps it.
            .setAutoCancel(true)
        nightNotification = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Night Azkar")
            .setContentText("Don't forget to read night azkar")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            // Automatically removes the notification when the user taps it.
            .setAutoCancel(true)
    }

}