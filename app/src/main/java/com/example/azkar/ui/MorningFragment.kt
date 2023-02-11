package com.example.azkar.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.example.azkar.R
import com.example.azkar.databinding.FragmentMorningBinding
import com.example.azkar.model.Azkar
import com.example.azkar.model.DataBase
import com.example.azkar.ui.adapter.AlarmUtils
import com.example.azkar.ui.adapter.OnListItemClicked
import com.example.azkar.ui.adapter.UserRecyclerView
import java.util.*


class MorningFragment : Fragment(), OnListItemClicked {

    private lateinit var binding: FragmentMorningBinding

    private val zekr= DataBase().morningAzkar()

    private val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView(zekr)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMorningBinding.inflate(inflater,container,false)
        setRetainInstance(true)
        return binding.root
    }


    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMorning.tvAppbar.setText(R.string.morning)
        binding.tbMorning.tvAppbar.setTextColor(Color.WHITE)
        binding.tbMorning.appbar.background = resources.getDrawable(R.drawable.day1)

        binding.rvList.adapter = userRecyclerView

        userRecyclerView.onListItemClicked = this

    }

    override fun onItemClicked(zkr: Azkar) {
        userRecyclerView.update(zkr)
        userRecyclerView.setColor(zkr,"morning")
//    Toast.makeText(context, "The user ${zkr.zkr} is deleted successfully",
//        Toast.LENGTH_SHORT).show()
    }
}