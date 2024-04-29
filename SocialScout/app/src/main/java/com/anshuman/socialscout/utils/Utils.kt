package com.anshuman.socialscout.utils

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import javax.security.auth.callback.Callback

// TODO 8 crete the function for the Upload Url
fun uploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {
    val imageUrl: String? = null
    val storageReference = FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())

    storageReference.putFile(uri)
        .addOnSuccessListener { taskSnapshot ->
            storageReference.downloadUrl.addOnSuccessListener { imageUrl ->
                callback(imageUrl.toString())
            }.addOnFailureListener { exception ->
                // Handle any errors with getting the download URL
                callback(null)
            }
        }
        .addOnFailureListener { exception ->
            // Handle any errors with uploading the file
            callback(null)
        }
}