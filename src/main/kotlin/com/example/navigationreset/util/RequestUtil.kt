package com.example.navigationreset.util

import com.example.navigationreset.domain.Navigation
import com.example.navigationreset.domain.UserAgentHeader
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.springframework.http.HttpHeaders

object RequestUtil {
    private const val RESET_URI = "https://api.socar.kr/car_tablet/cancel_reset_device"

    private fun navigationToHeader(navigation: Navigation): UserAgentHeader {
        val routerId = navigation.routerId
        return UserAgentHeader(routerId, SHA1.encryptString(routerId))
    }

    fun createResetRequest(navigation: Navigation): HttpGet {
        val header = navigationToHeader(navigation)

        return HttpGet(RESET_URI).apply {
            this.addHeader(HttpHeaders.USER_AGENT, header.toString())
        }
    }

    fun sendGetRequest(request: HttpGet): HttpResponse {
        return HttpClients.createDefault().use {
            it.execute(request)
        }
    }
}