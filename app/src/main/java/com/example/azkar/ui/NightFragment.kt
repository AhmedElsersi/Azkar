package com.example.azkar.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.azkar.R
import com.example.azkar.databinding.FragmentNightBinding
import com.example.azkar.model.Azkar
import com.example.azkar.model.DataBase
import com.example.azkar.ui.adapter.OnListItemClicked
import com.example.azkar.ui.adapter.UserRecyclerView


class NightFragment : Fragment(), OnListItemClicked {

    private lateinit var binding: FragmentNightBinding

    private var zekr= DataBase().nightAzkar()

    private val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView(zekr)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setRetainInstance(true)
        // Inflate the layout for this fragment
        binding = FragmentNightBinding.inflate(inflater,container,false)
        return binding.root
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbNight.tvAppbar.setText(R.string.night)
        binding.tbNight.tvAppbar.setTextColor(Color.WHITE)
        binding.tbNight.appbar.background = resources.getDrawable(R.drawable.night1)

        binding.rvList.adapter = userRecyclerView
        userRecyclerView.onListItemClicked = this
    }

    override fun onItemClicked(zkr: Azkar) {
        userRecyclerView.update(zkr)
        userRecyclerView.setColor(zkr,"night")
//        Toast.makeText(context, "The user ${zkr.zkr} is deleted successfully",
//            Toast.LENGTH_SHORT).show()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val json = Gson().toJson(zekr)
//        outState.putString("Azkar", json)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        var json : String?
//        if (savedInstanceState != null) {
//            json = savedInstanceState.getString("Azkar")
//            val type: Type = object : TypeToken<List<Azkar>>() {}.type
//            val result: List<Azkar> = Gson().fromJson(json, type)
//            zekr=result
////            println(result)
////            zekr= Gson().fromJson(json, type)
//            val userRecyclerView: UserRecyclerView by lazy {
//                UserRecyclerView(zekr)
//            }
//            binding.rvList.adapter = userRecyclerView
//            userRecyclerView.onListItemClicked = this
//        }
//    }

}