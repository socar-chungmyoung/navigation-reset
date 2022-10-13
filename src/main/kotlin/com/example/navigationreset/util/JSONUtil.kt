package com.example.navigationreset.util

import org.apache.http.HttpResponse
import org.apache.http.util.EntityUtils
import org.apache.tomcat.util.json.JSONParser

object JSONUtil {
    fun extractCodeAtResponse(response: HttpResponse): String {
        val json = EntityUtils.toString(response.entity, "UTF-8")
        return JSONParser(json).parseObject()["retCode"].toString()
    }
}