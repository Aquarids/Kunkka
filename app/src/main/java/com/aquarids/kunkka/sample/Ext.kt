package com.aquarids.kunkka.sample

import android.app.Activity
import android.content.Context
import android.os.Build

fun Activity.isActivityFinish(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        return this.isFinishing || this.isDestroyed
    }
    return this.isFinishing
}

fun Context?.isActivityFinished(): Boolean {
    this ?: return false
    if (this is Activity) {
        return this.isActivityFinish()
    } else {
        return true
    }
}