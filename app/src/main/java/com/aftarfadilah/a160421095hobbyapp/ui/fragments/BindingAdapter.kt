package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.aftarfadilah.a160421095hobbyapp.R
import com.squareup.picasso.Picasso


@BindingAdapter("android:imagesUrl")
fun loadImage(v:ImageView, url: String?){
    val parent = v.parent as? View
    var loadImage = parent?.findViewById<ProgressBar>(R.id.loadImage)

    if (url.isNullOrEmpty()) {
        v.visibility = View.GONE
    } else {
        val picasso = Picasso.Builder(v.context)
        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
        picasso.build().load(url).into(v)
        v.visibility = View.VISIBLE
        if(loadImage != null){
            loadImage.visibility = View.GONE
        }
    }
}

//@BindingAdapter("android:imagesUrl")
//fun loadImage(v:ImageView, url: String){
//    val picasso = Picasso.Builder(v.context)
//    picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//    picasso.build().load(url).into(v)
//    v.visibility = View.VISIBLE
//}