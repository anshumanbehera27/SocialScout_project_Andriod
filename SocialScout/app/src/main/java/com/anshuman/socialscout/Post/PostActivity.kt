package com.anshuman.socialscout.Post

import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anshuman.socialscout.Models.Post
import com.anshuman.socialscout.databinding.ActivityPostBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PostActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val bitmap = decodeUriToBitmap(it)
            saveImageToInternalStorage(bitmap)
            binding.selectImage.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.buttonPost.setOnClickListener {
            val caption = binding.Caption.editText?.text.toString().trim()
            val imageUri = getImageUriFromInternalStorage()
            val post = Post(imageUri.toString(), caption)
            // Do whatever you want with the post object
            finish()
        }
    }

    private fun decodeUriToBitmap(uri: Uri): Bitmap {
        return BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap) {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_${timeStamp}.jpg"
        val file = File(filesDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getImageUriFromInternalStorage(): Uri? {
        val directory = File(filesDir.toString() + "/images")
        val files = directory.listFiles()
        return if (files != null && files.isNotEmpty()) {
            val imagePath = files[0].absolutePath
            Uri.parse(imagePath)
        } else {
            null
        }
    }
}