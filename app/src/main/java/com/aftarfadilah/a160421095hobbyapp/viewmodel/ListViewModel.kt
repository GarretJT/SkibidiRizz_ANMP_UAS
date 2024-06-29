package com.aftarfadilah.a160421095hobbyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aftarfadilah.a160421095hobbyapp.model.Hobby
import com.aftarfadilah.a160421095hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    var homesLD =MutableLiveData<List<Hobby>>()
    val homesLoadErrorLD =MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
//    val TAG = "volleyTag"
//    private var queue:RequestQueue ?= null

    override val coroutineContext:CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        homesLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())
            homesLD.postValue(db.hobbyDao().selectAllHobby())
            loadingLD.postValue(false)
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/news.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object :TypeToken<List<News>>() {}.type
//                val result = Gson().fromJson<List<News>>(it, sType)
//                homesLD.value = result as ArrayList<News>?
//                loadingLD.value = false
//
//                Log.d("showVolley", result.toString())
//            },
//            {
//                Log.d("showVolley", it.toString())
//                loadingLD.value = false
//                homesLoadErrorLD.value = false
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

    fun clearNews(hobby: Hobby) {
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().deleteHobby(hobby)
            homesLD.postValue(db.hobbyDao().selectAllHobby())
        }
    }
}