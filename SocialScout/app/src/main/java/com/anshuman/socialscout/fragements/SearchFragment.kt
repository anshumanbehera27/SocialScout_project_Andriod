package com.anshuman.socialscout.fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Adapters.EventSearchAdapter
import com.anshuman.socialscout.Models.EventData
import com.anshuman.socialscout.R
import java.util.*


class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<EventData>()
    private lateinit var adapter: EventSearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addDataToList()
        adapter = EventSearchAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        return view


    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<EventData>()
            for (i in mList) {
                if (i.location.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        val eventTypes = listOf("Stand-up comedy", "Music concert", "Workshop", "Conference")
        val locations = listOf("Mumbai", "Delhi", "Bangalore", "Kolkata")

        // Shuffle event types and locations
        val shuffledEventTypes = eventTypes.shuffled()
        val shuffledLocations = locations.shuffled()

        // Initialize event index
        var eventIndex = 1

        // Add events of different types at random locations
        for (eventType in shuffledEventTypes) {
            for (location in shuffledLocations) {
                mList.add(EventData(eventType, location, getEventImageResource(eventIndex)))
                eventIndex++ // Increment event index
            }
        }

        // Add images c1 to c22
        for (i in 1..22) {
            mList.add(EventData("Event", "Location", getResourceId("c$i", "drawable")))
        }
    }

    // Utility function to get event image resource
    private fun getEventImageResource(eventIndex: Int): Int {
        // Assuming eventIndex starts from 1
        val resourceName = "c$eventIndex"
        return getResourceId(resourceName, "drawable")
    }

    // Utility function to get resource ID by name and type
    private fun getResourceId(resourceName: String, resourceType: String): Int {
        return try {
            // Constructing the resource ID dynamically
            val packageName = requireContext().packageName
            val resourceIdName = "$packageName:drawable/$resourceName"
            resources.getIdentifier(resourceIdName, null, null)
        } catch (e: Exception) {
            // Return a default image resource ID if resource not found
            R.drawable.c2
        }
    }


}