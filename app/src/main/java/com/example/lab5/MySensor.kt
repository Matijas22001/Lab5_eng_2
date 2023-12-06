package com.example.lab5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView


/* Example of sensor operation - gyroscope */

class MySensor(/*base constructor*/val activity: MainActivity) :/*sensor interface*/SensorEventListener {
    val label:TextView //the label in which the measurement will be displayed
        get() { // own getter to retrieve the label holder from the facade
            return activity.findViewById<TextView>(R.id.label)
        }

    //base constructor body
    init {
        //getting the sensor management service
        val sensorService = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //downloading a list of selected sensors - gyroscope here
        val sensorList = sensorService.getSensorList(Sensor.TYPE_GYROSCOPE)
        //downloading the first sensor from the list
        val thermometer = sensorList[0]
        //registration of an object of this class as a receiver of events - measurements from the sensor
        sensorService.registerListener(this,thermometer,SensorManager.SENSOR_DELAY_NORMAL)
    }

    //function called each time a new measurement comes in
    override fun onSensorChanged(event: SensorEvent?) {
        //inserting the measurement value into the label
        label.text=event?.values?.get(0)?.toString()
        //I take from the vector (array) only one dimension/parameter (in rad/s) for example
        //please pay attention to label.text - I use the default setter and operators ?.
    }

    //function called every time accuracy changes - not needed in the project now
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}