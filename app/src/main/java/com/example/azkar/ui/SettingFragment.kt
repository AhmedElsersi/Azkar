package com.example.azkar.ui

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.azkar.R
import com.example.azkar.databinding.FragmentSettingBinding
import com.example.azkar.ui.adapter.AlarmUtils
import java.util.Calendar

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = context?.getSharedPreferences("mypref",Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        var morningHour = sharedPreferences?.getInt("MorningHour",0)!!
        var morningMinute = sharedPreferences.getInt("MorningMinute",0)
        binding.btnMorningNotify.text = "${if(morningHour<10) "0$morningHour" else if (morningHour>12)morningHour-12 else morningHour}:" +
                "${if(morningMinute<10) "0$morningMinute" else morningMinute}" +" AM"
        var morningNotify = sharedPreferences.getBoolean("MorningNotify",false)
        binding.cbMorningNotify.isChecked = morningNotify
        
        var nightHour = sharedPreferences.getInt("NightHour",0)
        var nightMinute = sharedPreferences.getInt("NightMinute",0)
        binding.btnNightNotify.text = "${if(nightHour<10) "0$nightHour" else if (nightHour>12)nightHour-12 else nightHour}:" +
                "${if(nightMinute<10) "0$nightMinute" else nightMinute}"+" PM"
        var nightNotify = sharedPreferences.getBoolean("NightNotify",false)
        binding.cbNightNotify.isChecked = nightNotify

        binding.btnExpand.setOnClickListener {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (binding.hiddenView.visibility == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(binding.cvFirst, AutoTransition())
                }
                binding.hiddenView.visibility = View.GONE
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(binding.cvFirst, AutoTransition())
                }
                binding.hiddenView.visibility = View.VISIBLE
            }
        }

        binding.cbMorningNotify.setOnClickListener {
            editor?.apply{
                putBoolean("MorningNotify",binding.cbMorningNotify.isChecked)
                apply()
            }
            morningNotify = sharedPreferences.getBoolean("MorningNotify",false)
            binding.cbMorningNotify.isChecked = morningNotify
            if(morningNotify){
                editor?.apply{
                    putInt("AzkarType",1)
                    apply()
                }
//                val alarmUtils = context?.let { AlarmUtils(it) }
//                alarmUtils?.initRepeatingAlarm(1)
                val calendar = Calendar.getInstance()
                calendar.apply {
                    set(Calendar.HOUR_OF_DAY, morningHour)
                    set(Calendar.MINUTE, morningMinute)
                    set(Calendar.SECOND, 10)
                }
                val alarmUtils = context?.let { AlarmUtils(it) }
                alarmUtils?.initRepeatingAlarm(calendar,1)
            }
        }
        binding.btnMorningNotify.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)
                // set time to the button & variables
                editor?.apply{
                    putInt("MorningHour",hour)
                    putInt("MorningMinute",minute)
                    apply()
                }
                morningHour = sharedPreferences.getInt("MorningHour", 0)
                morningMinute = sharedPreferences.getInt("MorningMinute",0)
                binding.btnMorningNotify.text = "${
                    when {
                        morningHour<10 -> "0$morningHour"
                        morningHour>12 -> morningHour-12
                        else -> morningHour
                    }
                }:" +
                        "${if(morningMinute<10) "0$morningMinute" else morningMinute}"+
                        if (cal.get(Calendar.AM_PM) == Calendar.AM) " AM" else " PM"
//                var calendar= Calendar.getInstance()
                if(morningNotify){
                    editor?.apply{
                        putInt("AzkarType",1)
                        apply()
                    }
//                    if (cal.timeInMillis==calendar.timeInMillis){
                        val alarmUtils = context?.let { AlarmUtils(it) }
                        alarmUtils?.initRepeatingAlarm(cal,1)
//                    }
                }
            }
            TimePickerDialog(context,R.style.DialogTheme,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
        }

        binding.cbNightNotify.setOnClickListener {
            editor?.apply{
                putBoolean("NightNotify",binding.cbNightNotify.isChecked)
                apply()
            }
            nightNotify = sharedPreferences.getBoolean("NightNotify",false)
            binding.cbNightNotify.isChecked = nightNotify
            if(nightNotify){
                editor?.apply{
                    putInt("AzkarType",2)
                    apply()
                }
                val calendar = Calendar.getInstance()
                calendar.apply {
                    set(Calendar.HOUR_OF_DAY, nightHour)
                    set(Calendar.MINUTE, nightMinute)
                    set(Calendar.SECOND, 10)
                }
                val alarmUtils = context?.let { AlarmUtils(it) }
                alarmUtils?.initRepeatingAlarm(calendar,2)
            }
        }
        binding.btnNightNotify.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)
                // set time to the button & variables
                editor?.apply{
                    putInt("NightHour",hour)
                    putInt("NightMinute",minute)
                    apply()
                }
                nightHour = sharedPreferences.getInt("NightHour", 0)
                nightMinute = sharedPreferences.getInt("NightMinute",0)
                binding.btnNightNotify.text = "${if(nightHour<10) "0$nightHour" else if (nightHour>12)nightHour-12 else nightHour}:" +
                        "${if(nightMinute<10) "0$nightMinute" else nightMinute}"+
                        if (cal.get(Calendar.AM_PM) == Calendar.AM) " AM" else " PM"
//                var calendar= Calendar.getInstance()
                if(nightNotify){
//                    if (cal.timeInMillis==calendar.timeInMillis){
                    editor?.apply{
                        putInt("AzkarType",2)
                        apply()
                    }
                    val alarmUtils = context?.let { AlarmUtils(it) }
                    alarmUtils?.initRepeatingAlarm(cal,2)
//                    }
                }
            }
            TimePickerDialog(context,R.style.DialogTheme,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
        }

        binding.tbSetting.ibBack.setOnClickListener {
            val action = SettingFragmentDirections.actionSettingFragmentToMainFragment()
            findNavController().navigate(action)
        }
        binding.tbSetting.ibShare.setOnClickListener {
        }

    }

}