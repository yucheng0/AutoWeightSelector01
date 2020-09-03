package com.example.autoweightselector


import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

val TAG = "myTag"

class MainActivity : AppCompatActivity() {
companion object {
    var myViewModel: MyViewModel? =null
}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate1: $myViewModel")
            val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
            MainFragment.myViewModel = myViewModel
            Log.d(TAG, "onCreate: MainActivity = $myViewModel")
            QRCodeMainActivity.myViewModel = myViewModel
            myViewModel.context = this

 //       val intent = Intent (this,QRCodeMainActivity::class.java)
  //      startActivity(intent)
        var bundle = intent.getBundleExtra("bun")
        val data = bundle?.get("data2")
        Log.d(TAG, "data2:$data ")

    }  //onCreate


}