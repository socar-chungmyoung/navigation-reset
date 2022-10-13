package com.example.navigationreset.domain

data class Navigation(
    private val _routerId: String,
    var navigationStatus: String = ""
) {
    val routerId: String
        get() = _routerId.replace("-", "")

    fun changeStatus(status: String) {
        this.navigationStatus = status
    }

    fun isResetCompleted(): Boolean = navigationStatus == "1"
}
