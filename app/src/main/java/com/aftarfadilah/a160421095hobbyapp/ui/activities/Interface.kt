package com.aftarfadilah.a160421095hobbyapp.ui.activities

import android.view.View

interface UserSignUpClickListener{
    fun onUserSignUpClick(v: View)
}

interface UserLoginClickListener{
    fun onUserLoginClick(v: View)
}

interface UserChangeNameClickListener{
    fun onUserChangeNameClick(v: View)
}

interface UserChangePasswordClickListener{
    fun onUserChangePasswordClick(v: View)
}
interface ProfileUpdateClickListener {
    fun onChangeUsernameClick(v: View)
    fun onLogOutClick(v: View)
    fun onDeleteAccountClick(v: View)
}

interface ButtonActionNavClickListener {
    fun onButtonActionNavClick(v: View)
}
