package com.aquarids.kunkka

class DialogTag(@DialogManager.DialogPriority var priority: Int, var uuid: String) {

    override fun equals(other: Any?): Boolean {
        if (other == this) {
            return true
        } else if (other is DialogTag) {
            if (other.uuid == this.uuid && other.priority == this.priority) {
                return true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var result = priority
        result = 31 * result + uuid.hashCode()
        return result
    }
}