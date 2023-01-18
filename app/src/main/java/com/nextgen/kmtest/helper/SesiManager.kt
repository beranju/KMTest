package com.nextgen.kmtest.helper

import android.content.Context

class SesiManager(context: Context) {
    companion object{
        private const val PREF_NAME = "user"
        private const val NAME = "name"
    }

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setUser(name: String){
        val editor = pref.edit()
        editor.putString(NAME, name).commit()
    }

    fun getUser(): String{
        return pref.getString(NAME, "user").toString()
    }



}