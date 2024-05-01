package com.anshuman.socialscout.fragements

import StoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Adapters.InstaFeedAdapter
import com.anshuman.socialscout.Models.InstaFeedModel
import com.anshuman.socialscout.Models.StoryModel
import com.anshuman.socialscout.R

class HomeFragment : Fragment() {
    private val ADD_STORY_TYPE = 0
    private val ALL_STORY_TYPE = 1

    private lateinit var adapter: StoryAdapter
    private lateinit var feedAdapter: InstaFeedAdapter
    private lateinit var modelList: MutableList<StoryModel>
    private lateinit var feedModelList: MutableList<InstaFeedModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var feedRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.story_recyclerView_id)
        feedRecyclerView = view.findViewById(R.id.recyclerView_id)

        modelList = mutableListOf(
            StoryModel(ADD_STORY_TYPE, "1", R.drawable.profile1),
            StoryModel(ALL_STORY_TYPE, "2", "10", "Mariya", R.drawable.profile3),
            StoryModel(ALL_STORY_TYPE, "3", "11", "Jack", R.drawable.profile2),
            StoryModel(ALL_STORY_TYPE, "4", "13", "Alina", R.drawable.profile4),
            StoryModel(ALL_STORY_TYPE, "2", "10", "Mariya", R.drawable.profile3),
            StoryModel(ALL_STORY_TYPE, "3", "11", "Jack", R.drawable.profile2),
            StoryModel(ALL_STORY_TYPE, "4", "13", "Alina", R.drawable.profile4)
        )

        adapter = StoryAdapter(modelList)
        recyclerView.adapter = adapter

        feedModelList = mutableListOf(
            InstaFeedModel(R.drawable.profile2, "Jack", "USA", "https://marketplace.canva.com/EAFH_oMBen8/1/0/900w/canva-gray-and-white-asthetic-friend-instagram-story-C5KpyJG5MHA.jpg", "Hello, have a nice day", "3", "10/7/2023"),
            InstaFeedModel(R.drawable.profile4, "Alina", "USA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdl3HTGdSrPPtE1intiEqAGncJF0-HAyL6VpjWlBNG_wsroaBdglQkhczbEJ6rt5MeCg&usqp=CAU", "Hello, have a nice day", "8", "18/7/2023"),
            InstaFeedModel(R.drawable.profile3, "Mariya", "USA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6bQFqMhQmg9hJ-FA5xUUrQidHgQqZC5Nktw&usqp=CAU", "Hello, have a nice day", "13", "1/7/2023"),
            InstaFeedModel(R.drawable.profile2, "Jack", "USA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaYFjYA4n9jA30XMr0YMswgufCFTDoGO2-f0r1gZakb6badmzDJngUab4bCLGFCGTBAnU&usqp=CAU", "Hello, have a nice day", "4", "11/7/2023"),
            InstaFeedModel(R.drawable.profile4, "Alina", "USA", "https://marketplace.canva.com/EAFH_oMBen8/1/0/900w/canva-gray-and-white-asthetic-friend-instagram-story-C5KpyJG5MHA.jpg", "Hello, have a nice day", "17", "17/7/2023")
        )

        feedAdapter = InstaFeedAdapter(feedModelList)
        feedRecyclerView.adapter = feedAdapter

        return view
    }
}
