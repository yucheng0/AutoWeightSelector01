package com.example.autoweightselector
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    companion object{
        lateinit var job:Job
    }


    fun StartTimer() {
        viewModelScope.launch(Dispatchers.Main) {
            delay200ms()
        }
    }

    suspend fun delay200ms() {
        //耗時操作
        delay(200)

    }
}




