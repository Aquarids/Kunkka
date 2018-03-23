package com.aquarids.kunkka

abstract class DialogMixin(var dialogTag: DialogTag) : IDialog {

    var listener: DialogManagerListener? = null

    override fun show() {
        listener?.onDialogShow()
    }

    override fun dismiss() {
        listener?.onDialogDismiss()
    }
}