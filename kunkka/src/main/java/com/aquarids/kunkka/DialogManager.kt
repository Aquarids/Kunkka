package com.aquarids.kunkka

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.IntDef
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Zhaoqi Wang
 * on 2018/3/8.
 */
class DialogManager {

    companion object {
        // 数值越小，优先级越高
        const val PRIORITY_SUPER: Int = 0
        const val PRIORITY_HIGH: Int = 1
        const val PRIORITY_NORMAL: Int = 2
        const val PRIORITY_LOW: Int = 3
    }

    @IntDef(PRIORITY_SUPER, PRIORITY_HIGH, PRIORITY_NORMAL, PRIORITY_LOW)
    @Retention(AnnotationRetention.SOURCE)
    annotation class DialogPriority

    private var mIsRunning = false

    private val mDialogList = ArrayList<DialogMixin>()
    private var contextWeak: WeakReference<Context>? = null

    fun start(context: Context) {
        contextWeak = WeakReference(context)
        if (!mIsRunning) {
            contextWeak?.get()?.let {
                showDialog(it)
            }
        }
    }

    private fun showDialog(context: Context) {
        if (mDialogList.size <= 0) {
            return
        } else if (context is Activity && !context.isActivityFinish() && !mIsRunning) {
            mDialogList[0].show()
        }
    }

    fun addDialog(dialog: DialogMixin) {
        dialog.listener = listener
        if (mDialogList.size > 0) {
            mDialogList.add(findInsertPosition(dialog), dialog)
        } else {
            mDialogList.add(dialog)
        }
    }

    private val listener = object : DialogManagerListener {
        override fun onDialogShow() {
            mIsRunning = true
        }

        override fun onDialogDismiss() {
            mIsRunning = false
            mDialogList.removeAt(0)
            if (mDialogList.size <= 0) {
                return
            } else {
                contextWeak?.get()?.let {
                    showDialog(it)
                }
            }
        }
    }

    private fun findInsertPosition(dialog: DialogMixin): Int {
        mDialogList.forEachIndexed { index, it ->
            if (dialog.dialogTag.priority < it.dialogTag.priority) {
                return index
            }
        }
        return mDialogList.size
    }

    private fun Activity.isActivityFinish(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return this.isFinishing || this.isDestroyed
        }
        return this.isFinishing
    }
}