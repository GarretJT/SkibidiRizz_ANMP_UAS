package com.aftarfadilah.a160421095hobbyapp.util

import android.content.Context
import com.aftarfadilah.a160421095hobbyapp.model.HobbyDatabase

const val DB_NAME = "hobbyDB"

fun buildDb(context: Context): HobbyDatabase {
    return HobbyDatabase.invoke(context)
}
