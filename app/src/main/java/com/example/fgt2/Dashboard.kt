package com.example.fgt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        login_icon.setOnClickListener {
            Log.d("Signup","Try signing up")
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        description.setOnClickListener {
            val intent = Intent(this,Description::class.java)
            startActivity(intent)
        }

        other_sdg.setOnClickListener {
            val intent = Intent(this,OtherSDG::class.java)
            startActivity(intent)
        }

        share_ideas.setOnClickListener {
            val intent = Intent(this,ShareIdeas::class.java)
            startActivity(intent)
        }

        available_partnerships.setOnClickListener {
            val intent = Intent(this,AvailablePartnership::class.java)
            startActivity(intent)
        }

  }
}