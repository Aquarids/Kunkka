package com.aquarids.kunkka

abstract class DialogMixin(var dialogTag: DialogTag) : IDialog {

    var listener: DialogManagerListener? = null
}