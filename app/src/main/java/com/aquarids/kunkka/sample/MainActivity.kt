package com.aquarids.kunkka.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aquarids.kunkka.DialogManager

class MainActivity : AppCompatActivity() {

    private val mDialogManager by lazy { DialogManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDialogManager.addDialog(BottomSampleDialog(this))
        mDialogManager.addDialog(BottomSampleTwoDialog(this))
        mDialogManager.addDialog(BottomSampleThreeDialog(this))
        mDialogManager.start(this)
    }
}
