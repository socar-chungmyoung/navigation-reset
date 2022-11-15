package com.example.navigationreset.service

import com.example.navigationreset.domain.Navigation
import com.example.navigationreset.util.JSONUtil
import com.example.navigationreset.util.RequestUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

@Service
class NavigationService {
    fun resetNavigation(navigation: Navigation): Navigation {
        val request = RequestUtil.createResetRequest(navigation)
        val response = RequestUtil.sendGetRequest(request)
        navigation.changeStatus(JSONUtil.extractCodeAtResponse(response))

        return navigation
    }

    fun resetNavigationWithCoroutine(navigation: Navigation): Navigation {
        val request = RequestUtil.createResetRequest(navigation)
        val response = RequestUtil.sendGetRequest(request)
        navigation.changeStatus(JSONUtil.extractCodeAtResponse(response))

        return navigation
    }

    suspend fun resetAllNavigationWithCoroutine(navigationList: ArrayList<Navigation>): List<Navigation> {
        val deferredList = navigationList.asSequence()
            .filter { !it.isResetCompleted() }
            .map { CoroutineScope(Dispatchers.IO).async { resetNavigationWithCoroutine(it) } }
            .toList()

        return coroutineScope {
            deferredList.map { it.await() }
                .toList()
        }
    }

    // 비동기로 처리해야할 부분.
    fun resetAllNavigation(navigationList: List<Navigation>): List<Navigation> {
        val time = measureTimeMillis {
            for (navigation in navigationList) {
                if (!navigation.isResetCompleted())
                    resetNavigation(navigation)
            }
        }

        println(time)
        return navigationList
    }
}