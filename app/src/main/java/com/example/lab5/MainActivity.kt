package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //var mySensor :MySensor? = null //uncomment for task number 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //for task 1
        //stretching the canvas over the entire activity
        setContentView(MyGLSurfaceView(applicationContext))

        /* for task 2
        //for testing restoring the facade defined in xml
        setContentView(R.layout.activity_main)
        //new sensor object
        mySensor=MySensor(this)
        */

    }
}