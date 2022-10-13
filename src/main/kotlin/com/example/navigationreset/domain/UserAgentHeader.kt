package com.example.navigationreset.domain

import java.util.StringJoiner

data class UserAgentHeader(
    val routerId: String,
    val checkSum: String
) {

    private val DALVIK = "Dalvik/2.1.0"
    private val ENVIRONMENT = "(Linux; U; Android 5.0.2; MON8B Build/LRX22G)"
    private val APP_NAME = "SOCAR/1.0.0.0"
    private val MDN = "MDN"
    private val ENC_MDN = "ENCMDN"

    override fun toString(): String {
        val sj = StringJoiner(" ")
        sj.add(DALVIK)
        sj.add(ENVIRONMENT)
        sj.add(APP_NAME)
        sj.add("($MDN $routerId;")
        sj.add("$ENC_MDN $routerId" + "_$checkSum);")
        return sj.toString()
    }
}