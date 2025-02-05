package com.anshuman.socialscout.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

// you need to check for this adapter is good to go for this
// TODO 10 we need to creter the page Adapter for this
class ViewPagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm ,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ){

    val fragmentList = mutableListOf<Fragment>()
    val titleList = mutableListOf<String>()

    override fun getCount(): Int {
        return  fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get( position)
    }
     fun addFragments(fragment: Fragment , title: String){
        fragmentList.add(fragment)
       titleList.add(title)
    }
}