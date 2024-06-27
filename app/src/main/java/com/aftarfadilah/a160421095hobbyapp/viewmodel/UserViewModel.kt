package com.aftarfadilah.a160421095hobbyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aftarfadilah.a160421095hobbyapp.model.User
import com.aftarfadilah.a160421095hobbyapp.model.UserDao
import com.aftarfadilah.a160421095hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    private val userDao: UserDao = buildDb(application).userDao()

    val userLD = MutableLiveData<User>()
    fun register(user: User) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(user)
        }
    }

    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().loginUser(username, password))
        }
    }

    fun fetch(id: Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }

    fun update(user: User) {
        launch {
            buildDb(getApplication()).userDao().updateUser(user)
            userLD.postValue(user)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
