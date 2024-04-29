package com.anshuman.socialscout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_screen)
        window.statusBarColor = Color.TRANSPARENT

        // TODO 1 apply the splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().currentUser == null)
            startActivity(Intent(this , SignUp::class.java))
            else
                startActivity(Intent(this , Home_page::class.java))
            finish()
        }, 3000)


    }
}