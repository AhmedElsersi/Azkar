package com.example.azkar.ui.adapter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import java.util.*

class AlarmUtils(context: Context) {
    private var mContext = context
    private var alarmMgr: AlarmManager? = null
    private var alarmIntent: PendingIntent

    init {
        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { mIntent ->
            // if you want more than one notification use different requestCode
            // every notification need different requestCode
            PendingIntent.getBroadcast(mContext, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            PendingIntent.getBroadcast(mContext, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
/*    fun initRepeatingAlarm(type:Int) {
        val sharedPreferences = mContext?.getSharedPreferences("mypref", Context.MODE_PRIVATE)
        var mHour:Int = sharedPreferences?.getInt("MorningHour",0)!!
        var mMinute:Int = sharedPreferences.getInt("MorningMinute",0)
        var nHour:Int = sharedPreferences?.getInt("NightHour",0)!!
        var nMinute:Int = sharedPreferences.getInt("nightMinute",0)

        var calendar:Calendar=Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        if (type==1){
//        var calendar: Calendar=Calendar.getInstance().apply {
//            timeInMillis = System.currentTimeMillis()
//            set(Calendar.HOUR_OF_DAY, mHour)
//            set(Calendar.MINUTE, mMinute)
//            set(Calendar.SECOND,10) }
            calendar.set(Calendar.HOUR_OF_DAY, mHour)
            calendar.set(Calendar.MINUTE, mMinute)
            calendar.set(Calendar.SECOND,10)
    }else if(type==2){
            calendar.set(Calendar.HOUR_OF_DAY, nHour)
            calendar.set(Calendar.MINUTE, nMinute)
            calendar.set(Calendar.SECOND,10)
    }

        alarmMgr?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }*/
    fun initRepeatingAlarm(calendar: Calendar,type:Int){
        val sharedPreferences = mContext?.getSharedPreferences("mypref", Context.MODE_PRIVATE)
        var mHour:Int = sharedPreferences?.getInt("MorningHour",0)!!
        var mMinute:Int = sharedPreferences.getInt("MorningMinute",0)
        var nHour:Int = sharedPreferences.getInt("NightHour",0)
        var nMinute:Int = sharedPreferences.getInt("nightMinute",0)
        if (type==1){
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, mHour)
                set(Calendar.MINUTE, mMinute)
                set(Calendar.SECOND, 10)
            }
        }else if(type==2){
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, nHour)
                set(Calendar.MINUTE, nMinute)
                set(Calendar.SECOND, 10)
            }
        }


        alarmMgr?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr?.setExactAndAllowWhileIdle(  AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmIntent)        }
    }
}