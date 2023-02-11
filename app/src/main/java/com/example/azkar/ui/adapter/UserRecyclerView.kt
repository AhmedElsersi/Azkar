package com.example.azkar.ui.adapter

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.azkar.R
import com.example.azkar.model.Azkar

class UserRecyclerView(azkar: List<Azkar>) : RecyclerView.Adapter<UserRecyclerView.userViewHolder>() {

    var azkar:List<Azkar> = azkar
    var onListItemClicked : OnListItemClicked?= null

    fun update(zkr: Azkar){
        if (zkr.clicks < zkr.times){
        zkr.clicks +=1
        notifyDataSetChanged()
        }
    }

    fun setColor(zkr: Azkar,type:String){
        if (zkr.clicks==zkr.times){
            if (type=="night"){
                zkr.active=R.drawable.list_item_bg_night_active
            }else if (type=="morning"){
                zkr.active=R.drawable.list_item_bg_morning_active
            }
        }
    }

    inner class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tviZkr: TextView =itemView.findViewById(R.id.tviZkr)
        private var tviTimes:TextView=itemView.findViewById(R.id.tviTimes)
        private var tviClicks:TextView=itemView.findViewById(R.id.tviClicks)
        private var llZkr: LinearLayout =itemView.findViewById(R.id.llZkr)

        fun bind(zkr: Azkar){
            tviZkr.text= zkr.zkr
            tviTimes.text=zkr.times.toString()
            tviClicks.text= zkr.clicks.toString()
//            setColor(zkr)
            llZkr.setBackgroundResource(zkr.active)
//            if (zkr.active==1){
//                llZkr.setBackgroundResource(R.color.zkr_on) }
            itemView.setOnClickListener {
                onListItemClicked?.onItemClicked(zkr)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return userViewHolder(view)
    }

    override fun getItemCount(): Int {
        return azkar.size
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        var zkr: Azkar = azkar.get(position)
        holder.bind(zkr)
    }

}