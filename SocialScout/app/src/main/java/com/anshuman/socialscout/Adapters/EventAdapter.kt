package com.anshuman.socialscout.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Models.EventData
import com.anshuman.socialscout.R


// todo 16 Add the even adapter for the  show case the event view in the page
class EventAdapter(var mList: List<EventData> ) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.ivThumbnail)
        val titleTv : TextView = itemView.findViewById(R.id.tvEventTitle)
        val  location :TextView = itemView.findViewById(R.id.tvLocation)
        val bookButton: Button = itemView.findViewById(R.id.btnbook)

        init {
            bookButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Call a function to handle button click, passing the position if needed
                    onItemClick(position)
                }
            }
        }

    }
    private fun onItemClick(position: Int) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item_for_fragement , parent , false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
        holder.location.text = mList[position].location
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}