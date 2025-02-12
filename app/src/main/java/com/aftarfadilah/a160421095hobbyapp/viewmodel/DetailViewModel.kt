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


class DetailViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val homesDetailLD = MutableLiveData<Hobby>()

    private val job = Job()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetchDetail(id:Int){
        launch {
            val db = buildDb(getApplication())
            homesDetailLD.postValue(db.hobbyDao().selectHobbyById(id))
        }
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/detailNews.php?id=${id}"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url, {
//                homesDetailLD.value = Gson().fromJson(it, News::class.java)
//                Log.d("showVolley", it)
//            },
//            {
//                Log.d("ShowVolley", it.toString())
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }


}