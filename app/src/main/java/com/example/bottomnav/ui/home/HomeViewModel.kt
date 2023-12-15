package com.example.bottomnav.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.bindImage
import com.example.bottomnav.data.model.MarsPhoto
import com.example.bottomnav.network.MarsApi
import kotlinx.coroutines.launch
enum class MarsApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val _status = MutableLiveData<MarsApiStatus>()
    private val _photos = MutableLiveData<MarsPhoto>()


    // The external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus> = _status

    private var _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

     fun getMarsPhotos() {
        viewModelScope.launch {
            try {
               val listResult = MarsApi.retrofitService.getPhotos()
              //  _text.value = listResult
                _text.value = listResult.get(0).imgSrcUrl
                        /*"Success: ${listResult.size}" +
                        "id of the first json is ${listResult.get(0).id}\n"+
                        "the url is ${listResult.get(0).imgSrcUrl}"*/

                _photos.value = listResult[0]

                //  _status.value = listResult
            }
            catch (e: Exception) {
              //  _status.value = "Failure: ${e.message}"

            }
        }
    }

}