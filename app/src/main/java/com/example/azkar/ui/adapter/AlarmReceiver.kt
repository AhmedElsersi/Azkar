package com.example.azkar.ui.adapter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmReceiver:BroadcastReceiver() {
     override fun onReceive(context: Context?, mIntent: Intent?) {

         val sharedPreferences = context?.getSharedPreferences("mypref",Context.MODE_PRIVATE)
         var azkarType = sharedPreferences?.getInt("AzkarType",0)!!

        val notificationUtils = NotificationUtils(context)
        notificationUtils.launchNotification(azkarType)
//
//         val calendar = Calendar.getInstance()
//         if (Calendar.getInstance().after(calendar)) {
//             calendar.add(Calendar.DAY_OF_MONTH, 1)
//         }
//         val alarmUtils = AlarmUtils(context)
//         alarmUtils.initRepeatingAlarm(calendar, azkarType)
    }
}