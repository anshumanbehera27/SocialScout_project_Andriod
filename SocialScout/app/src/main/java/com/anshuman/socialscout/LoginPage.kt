package com.anshuman.socialscout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.anshuman.socialscout.Models.User
import com.anshuman.socialscout.databinding.ActivityLoginPageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginPage : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginPageBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener{
            if (binding.textEmail1.editText?.text.toString().equals("") or
                binding.textpassword1.editText?.text.toString().equals("")  ){
                Toast.makeText(this@LoginPage , "Please fill all deatils " , Toast.LENGTH_LONG).show()
            }else{
                var user = User(binding.textEmail1.editText?.text.toString() , binding.textpassword1.editText?.text.toString())

                Firebase.auth.signInWithEmailAndPassword(user.email!! , user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@LoginPage , Home_page::class.java))
                            finish()

                        }else{
                            Toast.makeText(this@LoginPage ,it.exception?.localizedMessage , Toast.LENGTH_LONG ).show()
                        }
                    }
            }
        }

    }
}