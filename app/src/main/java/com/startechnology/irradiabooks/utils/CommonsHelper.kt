package com.startechnology.irradiabooks.utils

import android.content.Context
import java.io.IOException

fun readJSON(context: Context, fileName:String):String?{

    val json:String
    try {

        val inputStream = context.assets.open(fileName)
        var size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use {
            it.read(buffer)
        }

        json = String(buffer)

    }catch (e: IOException){
        e.printStackTrace()
        return null
    }
    return json
}