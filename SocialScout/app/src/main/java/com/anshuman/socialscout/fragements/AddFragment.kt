package com.anshuman.socialscout.fragements

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anshuman.socialscout.Post.EventActivity
import com.anshuman.socialscout.Post.PostActivity
import com.anshuman.socialscout.R
import com.anshuman.socialscout.R.layout.fragment_add
import com.anshuman.socialscout.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {
    private  lateinit var binding : FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentAddBinding.inflate( inflater , container , false)

        binding.Post.setOnClickListener{
            activity?.startActivity(Intent(requireContext() , PostActivity ::class.java))
        }
        binding.event.setOnClickListener{
            activity?.startActivity(Intent(requireContext() , EventActivity::class.java))
        }

        return  binding.root
    }


}