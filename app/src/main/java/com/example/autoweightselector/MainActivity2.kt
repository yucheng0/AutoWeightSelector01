package com.example.autoweightselector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.d(TAG, "Hahia lldldfds ")
 //       var intent2 = Intent(this, MainActivity::class.java)
   //     startActivity(intent2)
        btnJump.setOnClickListener {    finish()  }

    }
}