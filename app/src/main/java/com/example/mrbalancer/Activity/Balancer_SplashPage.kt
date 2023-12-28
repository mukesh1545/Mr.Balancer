package com.example.mrbalancer.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mrbalancer.R

class Balancer_SplashPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balancer_splash_page)

         Handler().postDelayed({
             startActivity(Intent(this,Balancer_MainActivity::class.java))
             finish()
         },1000)

    }
}