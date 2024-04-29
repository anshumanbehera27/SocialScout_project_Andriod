package com.anshuman.socialscout.fragements


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anshuman.socialscout.Adapters.ViewPagerAdapter
import com.anshuman.socialscout.Models.User
import com.anshuman.socialscout.databinding.FragmentProfileBinding
import com.anshuman.socialscout.utils.USER_Node
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentProfileBinding.inflate(inflater, container, false)
//
//        viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
//        viewPagerAdapter.addFragments(MyPost() , "MY POST")
//        viewPagerAdapter.addFragments(Myevent() , "MY EVENT")
//        binding.Viewpager.adapter = viewPagerAdapter
//        binding.Tablayout.setofwithViewpager(binding.Viewpager)
//        return binding.root
//    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_Node).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user:User = it.toObject<User>()!!
                binding.namep.text = user.name
                binding.bio.text = user.email
                if(user.image.isNullOrEmpty()){
                    Picasso.get().load(user.image).into(binding.profileImage)
                }
            }
    }

}