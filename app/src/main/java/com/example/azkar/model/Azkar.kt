package com.example.azkar.model

import com.example.azkar.R

data class Azkar(
    var zkr:String,
    var times: Int,
    var clicks: Int = 0,
    var active: Int = R.drawable.list_item_bg_passive
    )