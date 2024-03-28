package com.example.toaster

import android.content.Context
import android.widget.Toast

class Toaster {

    companion object {
        @JvmStatic // Optional annotation to expose the method as a static method in Java
        fun makeToast(context:Context, message:String) {
            // Your function logic goes here
            println("This is a static-like function")
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()        }
    }
}