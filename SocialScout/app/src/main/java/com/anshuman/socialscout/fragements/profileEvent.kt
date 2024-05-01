package com.anshuman.socialscout.fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.anshuman.socialscout.Adapters.ImageAdapter
import com.anshuman.socialscout.R


class profileEvent : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_myevent, container, false)

        val gridView: GridView = view.findViewById(R.id.gridView)
        val imageArray = intArrayOf(R.drawable.c1, R.drawable.c2, R.drawable.c3,
                R.drawable.c4, R.drawable.c5, R.drawable.c6
                                                                                 )
        val adapter = ImageAdapter(requireContext(), imageArray)
        gridView.adapter = adapter

        return view
    }

}