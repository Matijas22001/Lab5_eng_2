package com.example.lab5

import android.content.Context
import android.opengl.GLSurfaceView

/* Canvas implementation */

class MyGLSurfaceView(context: Context?) :GLSurfaceView(context) {
    init {
        //setting the renderer (painter) in the body of the base constructor
        setRenderer(MyRenderer())
    }
}