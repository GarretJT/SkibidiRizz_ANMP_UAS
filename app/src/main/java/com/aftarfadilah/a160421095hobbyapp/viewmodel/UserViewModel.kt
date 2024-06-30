package com.aftarfadilah.a160421095hobbyapp.viewmodel

import android.app.Application
import android.content.Context
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

    val userLD = MutableLiveData<User?>()

    fun register(user: User) {
        launch {
            userDao.insertAll(user)
        }
    }
    fun deleteUser() {
        launch {
            val db = buildDb(getApplication())
            val currentUser = userLD.value
            currentUser?.let {
                db.userDao().deleteUser(currentUser)
            }
        }
    }


    fun changeUsername(newUsername: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            val currentUser = userLD.value
            if (currentUser?.password == password) {
                currentUser.username = newUsername
                db.userDao().updateUser(currentUser)
                userLD.postValue(currentUser)
            } else {
                // Handle password mismatch
            }
        }
    }

    fun logoutUser() {
        userLD.postValue(null) // Clear the user data
        // Perform any additional logout actions as needed
    }

    fun login(username: String, password: String) {
        launch {
            userLD.postValue(userDao.loginUser(username, password))
        }
    }

    fun fetch(id: Int) {
        launch {
            userLD.postValue(userDao.selectUser(id))
        }
    }

    fun update(user: User) {
        launch {
            userDao.updateUser(user)
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
