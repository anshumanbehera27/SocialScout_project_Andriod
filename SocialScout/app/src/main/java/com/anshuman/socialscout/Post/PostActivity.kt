package com.anshuman.socialscout.Post

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anshuman.socialscout.Models.Post
import com.anshuman.socialscout.databinding.ActivityPostBinding
import com.anshuman.socialscout.utils.POST
import com.anshuman.socialscout.utils.POST_FOLDER
import com.anshuman.socialscout.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    var ImageUrl:String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { imageUrl ->
               if(imageUrl!= null){
                   binding.selectImage.setImageURI(uri)
               ImageUrl = imageUrl
               }

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener{
            finish()
        }
        binding.selectImage.setOnClickListener{
            launcher.launch("image/*")
        }

        binding.buttonPost.setOnClickListener {
            val post:Post = Post(ImageUrl!!, binding.Caption.editText?.text.toString())

            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                finish()
            }
        }

    }

}