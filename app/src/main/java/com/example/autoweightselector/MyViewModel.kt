package com.example.autoweightselector

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*

class MyViewModel : ViewModel() {
    var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    lateinit var context: Context

    companion object {
        lateinit var job: Job
        lateinit var mBluetoothAdapter: BluetoothAdapter
        lateinit var btSocket: BluetoothSocket
    }


    fun StartTimer() {
        viewModelScope.launch(Dispatchers.Main) {
            delay200ms()
        }
    }

    suspend fun delay200ms() {
        //耗時操作
        delay(200)
        readData()          //執行資料的藍芽讀取

    }

 fun CheckBt() {
        Toast.makeText(context, "It has started", Toast.LENGTH_SHORT).show()
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

     // .enable會自動啟動藍芽,它會回傳BOOLEAN 是否可以正常啟動?
     //後續的toast會馬上蓋掉之前的未顯示完成的toast （有的會等有的不停,奇怪了）
        if (!mBluetoothAdapter.enable()) {
            Toast.makeText(context, "Bluetooth Disabled !", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "mBluetoothAdapter Disbaled")
            /* It tests if the bluetooth is enabled or not, if not the app will show a message. */
            //finish()  //????????
       } else {
            Toast.makeText(context, "Bluetooth Enabled !", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "mBluetoothAdapter Enabled")}
 }


   fun Connect() {
        viewModelScope.launch(Dispatchers.Main) {
            //   Toast.makeText(context, "HiHiHi", Toast.LENGTH_SHORT).show()
            //    connectwithCoroutines()
            //耗時操作
           val device =
               mBluetoothAdapter.getRemoteDevice("24:79:F3:8E:55:AA")   //Renox10 mac  配對
            Log.d(TAG, "Connecting to ... $device")
 //           Toast.makeText(
 //               context,
  //              "Connecting to ... ${device.name} mac: ${device.uuids[0]} address: ${device.address}",
 //               Toast.LENGTH_LONG
 //           ).show()
          mBluetoothAdapter.cancelDiscovery()
            try {
                btSocket = device.createRfcommSocketToServiceRecord(myUUID)
                /* Here is the part the connection is made, by asking the device to create a RfcommSocket (Unsecure socket I guess), It map a port for us or something like that */
                btSocket.connect()
                Log.d(TAG, "Connection made.")
  //              Toast.makeText(context, "Connection mad", Toast.LENGTH_SHORT).show()


            } catch (e: IOException) {
                try {
                    btSocket.close()
                } catch (e2: IOException) {
                    Log.d(TAG, "Unable to end the connection")
   //                 Toast.makeText(context, "Unable to end the connection", Toast.LENGTH_SHORT)
    //                    .show()
                }
                Log.d(TAG, "Socket creation failed")
  //              Toast.makeText(context, "Socket creation failed", Toast.LENGTH_SHORT).show()
            }
            //beginListenForData()
            //this is a method used to read what the Arduino says for example when you write Serial.print("Hello world.") in your Arduino code
        }
    }


   /* suspend fun connectwithCoroutines() {
           withContext(Dispatchers.IO) {
               Toast.makeText(context, "ssssssss", Toast.LENGTH_SHORT).show()
           }
       } */
    //耗時操作
    /*       val device =
                mBluetoothAdapter.getRemoteDevice("24:79:F3:8E:55:AA")   //Renox10 mac  配對
            Log.d("myTag", "Connecting to ... $device")
       Toast.makeText(context,"Connecting to ... ${device.name} mac: ${device.uuids[0]} address: ${device.address}",
               Toast.LENGTH_LONG).show()
            mBluetoothAdapter.cancelDiscovery()
            try {
                btSocket = device.createRfcommSocketToServiceRecord(myUUID)
                /* Here is the part the connection is made, by asking the device to create a RfcommSocket (Unsecure socket I guess), It map a port for us or something like that */
                btSocket.connect()
                Log.d("myTag", "Connection made.")
               Toast.makeText(context, "Connection mad", Toast.LENGTH_SHORT).show()


            } catch (e: IOException) {
                try {
                    btSocket.close()
                } catch (e2: IOException) {
                    Log.d("myTag", "Unable to end the connection")
                Toast.makeText(context, "Unable to end the connection", Toast.LENGTH_SHORT)
                        .show()
                }
                Log.d("myTag", "Socket creation failed")
           Toast.makeText(context, "Socket creation failed", Toast.LENGTH_SHORT).show()
            }
            //beginListenForData() */
    /* this is a method used to read what the Arduino says for example when you write Serial.print("Hello world.") in your Arduino code
}
//  }  */


    fun writeData(data: String) {
        var outStream = btSocket.outputStream
        try {
            outStream = btSocket.outputStream
        } catch (e: IOException) {
            //Log.d(FragmentActivity.TAG, "Bug BEFORE Sending stuff", e)
        }
        val msgBuffer = data.toByteArray()
        try {
            outStream.write(msgBuffer)
        } catch (e: IOException) {
            //Log.d(FragmentActivity.TAG, "Bug while sending stuff", e)
        }
    }

    fun readData() {
        var inStream = btSocket.inputStream
        try {
            inStream = btSocket.inputStream
        } catch (e: IOException) {
        }

        var buffer: ByteArray = ByteArray(1024)
        val sb = StringBuffer()
        var numBytes: Int // bytes returned from read()
        numBytes = inStream.read(buffer)  //3個在buffer內
        for (i in 0..numBytes - 1) {   //先知道nubBytes的數字再去讀
            sb.append(buffer[i])
        }
        println(sb)         // 讀出491310 的值

    }


}




