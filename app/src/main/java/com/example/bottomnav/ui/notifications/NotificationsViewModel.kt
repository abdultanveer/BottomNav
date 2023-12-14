package com.example.bottomnav.ui.notifications

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    lateinit var timer: CountDownTimer

    private  val _seconds = MutableLiveData<Int>()
    //val seconds = LiveData<Int>()

    var number = 0


    fun seconds():LiveData<Int>{
        return _seconds
    }
    fun startTime(){
        timer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                var timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}