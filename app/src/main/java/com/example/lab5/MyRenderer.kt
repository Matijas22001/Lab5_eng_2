package com.example.lab5

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import android.opengl.GLU
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/* Implementation of a renderer - a painter on a canvas */

class MyRenderer : /*Inheritance*/GLSurfaceView.Renderer {
    private var angle=0f; //angle for rotating the figure on the canvas

    //function called when creating canvas
    override fun onSurfaceCreated(gl: GL10?, p1: EGLConfig?) {

    }

    //function called when changing and creating canvas
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        /* OpenGL is a non-object-implemented engine, so its features are available
        in wrappers in the gl
         */
        //viewport configuration
        gl?.glViewport(0, 0, width, height)
        //loading the identity matrix as the projection matrix
        gl?.glMatrixMode(GL10.GL_PROJECTION)
        gl?.glLoadIdentity()
        //perspective configuration, we use GLU, i.e. OpenGL tools
        GLU.gluPerspective(gl, 45.0f, width.toFloat() / height.toFloat(), -1.0f, -10.0f)
        //setting the default color in the RGBA color model
        gl?.glClearColor(0.0f, 0.0f, 1.0f, 1.0f)
    }

    //a function called in a loop responsible for rendering (painting) the canvas
    override fun onDrawFrame(gl: GL10?) {
        //triangle vertices in 3D
        val vertices = floatArrayOf(
            -1.0f, 0.0f, -1.0f,
            0.0f, 1.0f, -1.0f,
            1.0f, 0.0f, -1.0f
        )
        //the vertices need to be passed outside the JVM so you need to allocate a buffer
        //3 - vertices, 3 - dimensions 4 - bytes per float
        val buffer: ByteBuffer = ByteBuffer.allocateDirect(3 * 3 * 4)
        //the hardware-native byte arrangement in the machine word
        buffer.order(ByteOrder.nativeOrder())
        //we want to refer to this buffer as a buffer of floats
        val verticesBuffer: FloatBuffer = buffer.asFloatBuffer()
        //we push the vertices to the buffer outside the JVM
        verticesBuffer.put(vertices)
        //we set the position in the buffer
        verticesBuffer.position(0)
        //color buffer and depth buffer configuration
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        //paint and fill color setting
        gl?.glColor4f(1.0f, 0.0f, 0.0f, 1.0f)
        //loading the identity matrix into the transformation matrix
        gl?.glLoadIdentity()
        //rotation of the figure, i.e. multiplication of the transformation matrix
        gl?.glRotatef(angle++ /*angle of rotation*/, 0.0f, 0.0f, 1.0f /*axis of rotation*/)
        //we go to the state of passing the vertices to the OpenGL engine
        gl?.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        //we pass information about the vertices to the engine
        gl?.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer)
        //we commission painting a triangle
        gl?.glDrawArrays(GL10.GL_TRIANGLES, 0 /*beginning*/, 3/*number of vertices*/)
        //we leave this state
        gl?.glDisableClientState(GL10.GL_VERTEX_ARRAY)
    }
}