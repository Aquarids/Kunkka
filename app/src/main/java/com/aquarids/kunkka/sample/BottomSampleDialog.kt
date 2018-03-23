package com.aquarids.kunkka.sample

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.widget.TextView
import com.aquarids.kunkka.DialogManager
import com.aquarids.kunkka.DialogMixin
import com.aquarids.kunkka.DialogTag

class BottomSampleDialog(var context: Context) : DialogMixin(DialogTag(DialogManager.PRIORITY_LOW, DialogConstant.DIALOG_TEST)) {

    private val mDialog by lazy { BottomSheetDialog(context) }

    private val mTvClose: TextView?

    init {
        mDialog.setContentView(R.layout.view_bottom_check_in)
        mTvClose = mDialog.findViewById(R.id.tv_close)

        mTvClose?.setOnClickListener {
            dismiss()
        }
    }

    override fun show() {
        mDialog.show()
    }

    override fun dismiss() {
        mDialog.dismiss()
    }
}