package com.example.azkar.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.azkar.R
import com.example.azkar.databinding.FragmentMainBinding
import com.example.azkar.ui.adapter.AlarmUtils
import java.lang.reflect.Type
import java.util.Calendar

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("Range", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var hour:Int
//        var minute:Int
//
//        var morningNotify=false
////        var nightNotify=false
////        var notifyOrNot=false

        binding.tbMain.tvAppbar.textSize = 30f
        binding.tbMain.ibSetting.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
        }

//        val sharedPreferences = context?.getSharedPreferences("mypref", Context.MODE_PRIVATE)
//        hour = sharedPreferences?.getInt("MainHour",0)!!
//        minute = sharedPreferences.getInt("MainMinute",0)
//        morningNotify = sharedPreferences?.getBoolean("MorningNotify",false)!!
//        println(morningNotify)
//        println(hour)
//        println(minute)
//
//        val alarmUtils = context?.let { AlarmUtils(it) }
//        if(morningNotify){
//            alarmUtils?.initRepeatingAlarm()
//        }

        binding.btnMorning.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_morningFragment)
        }

        binding.btnNight.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_nightFragment)
        }
    }
}
/*
fun intializeNotification(context: Context,calendar:Calendar,type: Int){
    val alarmUtils = AlarmUtils(context)
    alarmUtils.initRepeatingAlarm(calendar,type)
}*/
