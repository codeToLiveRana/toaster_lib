package com.example.toaster

import android.content.Context
import android.widget.Toast

class Toaster {
    fun staticSimpleToast(context:Context, message:String) {
        // Your function logic goes here
        println("This is a static-like function")
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}