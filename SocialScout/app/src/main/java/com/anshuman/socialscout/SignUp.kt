package com.anshuman.socialscout

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anshuman.socialscout.Models.User
import com.anshuman.socialscout.databinding.ActivitySignUpBinding
import com.anshuman.socialscout.utils.USER_Node
import com.anshuman.socialscout.utils.USER_PROFILE_FOLDER
import com.anshuman.socialscout.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) { imageUrl ->
                user.image = imageUrl
                binding.profileImage.setImageURI(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = User()

        binding.signUp.setOnClickListener {
            val name = binding.textname.editText?.text.toString()
            val email = binding.textEmail.editText?.text.toString()
            val password = binding.textpassword.editText?.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all Information", Toast.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ result ->
                        if (result.isSuccessful) {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@SignUp, Home_page::class.java))
                            intent.putExtra("Name" , name)
                            intent.putExtra("email" , email)

                            // TODO Firebase Store
                            val currentUser = FirebaseAuth.getInstance().currentUser
                            currentUser?.uid?.let { uid ->
                                val newUser = User(name, email, password)
                                FirebaseFirestore.getInstance().collection(USER_Node).document(Firebase.auth.currentUser!!.uid).set(newUser)
                                    .addOnSuccessListener {
                                        Toast.makeText(this ,"Your data is ass" , Toast.LENGTH_LONG).show()
                                       // startActivity(Intent(this@SignUp, Home_page::class.java))

                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_LONG)
                                            .show()
                                    }
                            }


                        } else {
                            Toast.makeText(this, "Error: ${result.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.textlogin.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
}
