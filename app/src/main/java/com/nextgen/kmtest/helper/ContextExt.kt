package com.nextgen.kmtest.helper

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.showAlertDialog(message: String){
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton("OK"){d, _ ->
            d.cancel()
        }
    }.show()
}