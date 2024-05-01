import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Models.StoryModel
import com.anshuman.socialscout.R
import com.bumptech.glide.Glide


class StoryAdapter(private val modelList: List<StoryModel>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    companion object {
        private const val ADD_STORY_TYPE = 0
        private const val ALL_STORY_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = if (viewType == ADD_STORY_TYPE) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.story_item_add, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.story_item, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modelList[position]
        val viewType = getItemViewType(position)
        if (viewType == ADD_STORY_TYPE) {
            val uid = model.uid
            val image = model.image
            holder.setAddStory(uid, image)
        } else {
            val uid = model.uid
            val sid = model.sid
            val name = model.name
            val image = model.image
            holder.setStory(uid, sid, name, image)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ADD_STORY_TYPE else ALL_STORY_TYPE
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title_id)
        private val profileImg: ImageView = itemView.findViewById(R.id.userProfile_id)

        fun setAddStory(uid: String, image: Int) {
            title.text = "Add Story"
            Glide.with(itemView.context)
                .load(image)
                .into(profileImg)
        }

        fun setStory(uid: String, sid: String?, name: String?, image: Int) {
            title.text = name
            Glide.with(itemView.context)
                .load(image)
                .into(profileImg)
        }
    }
}
