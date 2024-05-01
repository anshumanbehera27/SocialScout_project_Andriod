package com.anshuman.socialscout.fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anshuman.socialscout.Adapters.EventAdapter
import com.anshuman.socialscout.Models.EventData
import com.anshuman.socialscout.R

class EventFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter
    private var eventList = mutableListOf<EventData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = EventAdapter(eventList)
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add sample data to your eventList
        // This can be done in onViewCreated() or any other appropriate place
        addDataToList()
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
                eventList.add(EventData(eventType, location, getEventImageResource(eventIndex)))
                eventIndex++ // Increment event index
            }
        }

        // Add images c1 to c22
        for (i in 1..22) {
            eventList.add(EventData("Event", "Location", getResourceId("c$i", "drawable")))
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