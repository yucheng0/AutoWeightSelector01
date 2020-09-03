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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class QRCodeMainActivity : AppCompatActivity() {
    private lateinit var svBarcode: SurfaceView

    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
companion object{
    lateinit var myViewModel: MyViewModel
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_code_main)
//得到傳遞來的資料
        var bundle = intent.getBundleExtra("bun")
        val data = bundle?.get("data")
        Log.d(TAG, "data:$data ")

//  測試馬上傳回去
       MainFragment.getrightqrcodemacvalue01 = "00:44:55:77:99:88"
      Log.d(TAG, "onCreate:${myViewModel}")
        MainFragment.myViewModel.getrightqrcodemacvalue ="00:11:22:33:44:55"


        var intent2 = Intent(this, MainActivity::class.java)
        var str = "I am yct2"
//用資料捆傳遞資料
        val bundle1 = Bundle()
        bundle1.putString("data2", str)
//把資料捆設定改意圖
        intent2.putExtra("bun", bundle1)  //傳遞的資料
//啟用意圖
  //      startActivity(intent2)
        finish()            // 結束這個就跳回去了, 此時viewmodel 不會被重建

//====================================
//
        val builder = AlertDialog.Builder(this)
        var taskHandler = Handler()
        var runnable = object : Runnable {
            override fun run() {
                cameraSource.stop()
                val alert = builder.create()
                alert.show()
                taskHandler.removeCallbacksAndMessages(null)
            }
        }

        svBarcode = findViewById(R.id.sv_barcode)

        detector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()
        detector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}

            @SuppressLint("MissingPermission")
            override fun receiveDetections(p0: Detector.Detections<Barcode>?) {
                val barcodes = p0?.detectedItems
                if (barcodes!!.size() > 0) {
                    builder.setMessage(barcodes.valueAt(0).displayValue)
                    builder.setPositiveButton("關閉") { dialog, which ->
                        cameraSource.start(svBarcode.holder)
                    }
                    taskHandler.post(runnable)
                }
            }
        })
        cameraSource = CameraSource.Builder(this, detector).setRequestedPreviewSize(1024, 768)
            .setRequestedFps(30f).setAutoFocusEnabled(true).build()
        svBarcode.holder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceRedrawNeeded(holder: SurfaceHolder?) {
                print("1")
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
                print("2")
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                print("3")
                cameraSource.stop()
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                print("4")
                if (ContextCompat.checkSelfPermission(
                        this@QRCodeMainActivity,
                        android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                )
                    cameraSource.start(svBarcode.holder)
                else ActivityCompat.requestPermissions(
                    this@QRCodeMainActivity,
                    arrayOf(android.Manifest.permission.CAMERA),
                    123
                )
            }

        })
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                cameraSource.start(svBarcode.holder)
            else Toast.makeText(this, "scanner", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detector.release()
        cameraSource.stop()
        cameraSource.release()
    }
}
