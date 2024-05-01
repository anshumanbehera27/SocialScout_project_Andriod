package com.anshuman.socialscout.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anshuman.socialscout.fragements.SaveEvent
import com.anshuman.socialscout.fragements.profileEvent

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    // Your implementation here
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  if (position == 0)
            profileEvent()
        else
            SaveEvent()
    }
}