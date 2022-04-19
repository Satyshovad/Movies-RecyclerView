package com.example.movieapp
import android.view.View

data class Movie(
    val movieTitle : String,
    val movieDescription : String,
    val moviePhoto : Int ? = null
)
