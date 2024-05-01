package com.anshuman.socialscout.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Models.InstaFeedModel
import com.bumptech.glide.Glide
import com.anshuman.socialscout.R

class InstaFeedAdapter(private val modelList: List<InstaFeedModel>) :
    RecyclerView.Adapter<InstaFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.insta_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modelList[position]
        holder.setItems(model, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userProfile: ImageView = itemView.findViewById(R.id.userProfile_id)
        private val userProfile2: ImageView = itemView.findViewById(R.id.userProfile2_id)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val usernameId: TextView = itemView.findViewById(R.id.username_id)
        private val locationId: TextView = itemView.findViewById(R.id.location_id)
        private val statusId: TextView = itemView.findViewById(R.id.status_id)
        private val commentCountId: TextView = itemView.findViewById(R.id.comment_count_id)
        private val dateId: TextView = itemView.findViewById(R.id.date_id)

        fun setItems(model: InstaFeedModel, context: Context) {
            Glide.with(context).load(model.image).circleCrop().into(userProfile)
            Glide.with(context).load(model.image).circleCrop().into(userProfile2)
            Glide.with(context).load(model.feedImg).into(imageView)

            usernameId.text = model.username?.trim() ?: ""
            locationId.text = model.location?.trim() ?: ""
            statusId.text = model.status?.trim() ?: ""
            dateId.text = model.date?.trim() ?: ""
            commentCountId.text = "View all ${model.commentCount} comments"
        }
    }
}
