package com.anshuman.socialscout.Adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Models.EventData
import com.anshuman.socialscout.R

// todo 15 Add the even adapter for the  show case the event view in the page
class EventSearchAdapter(var mList: List<EventData>) :
    RecyclerView.Adapter<EventSearchAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
        val  location :TextView = itemView.findViewById(R.id.eventLocation)

    }

    fun setFilteredList(mList: List<EventData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
        holder.location.text = mList[position].location
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}