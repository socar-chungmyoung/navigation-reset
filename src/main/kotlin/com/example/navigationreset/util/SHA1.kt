package com.example.navigationreset.util

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

object SHA1 {
    private const val ENCRYPT_ALGORITHM = "SHA-1"

    fun encryptString(routerId: String): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHH")
        val date = Date()
        val sb = StringBuffer()

        sb.append(routerId)
        sb.append("_")
        sb.append(dateFormat.format(date))

        return stringToSHA(sb.toString())
    }

    private fun stringToSHA(text: String): String {
        val md = MessageDigest.getInstance(ENCRYPT_ALGORITHM)
        md.update(text.toByteArray(Charsets.ISO_8859_1), 0, text.length)
        return convertToHex(md.digest())
    }

    private fun convertToHex(digest: ByteArray): String {
        val sb = StringBuilder()
        for (byte in digest) {
            val halfByte = byte.toInt() and 0x0F
            var twoHalves = 0

            do {
                sb.append(halfByteToChar(halfByte))
            } while (twoHalves++ < 1)
        }

        return sb.toString()
    }

    private fun halfByteToChar(halfByte: Int): Char {
        return when ((0 <= halfByte) && (halfByte <= 9)) {
            true -> ('0' + halfByte)
            false -> ('a' + (halfByte - 10))
        }
    }
}