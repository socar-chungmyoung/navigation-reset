package com.example.navigationreset.controller

import com.example.navigationreset.domain.Navigation
import com.example.navigationreset.service.NavigationService
import com.example.navigationreset.util.CsvConvertUtil
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class NavigationController(
    private val navigationService: NavigationService
) {
    @GetMapping
    fun resetNavigation(@RequestParam _routerId: String): Navigation {
        val navigation = Navigation(_routerId)
        return navigationService.resetNavigation(navigation)
    }

    @PostMapping
    suspend fun resetNavigationWithCsv(@RequestParam("file") file: MultipartFile): List<Navigation> {
        return coroutineScope { navigationService.resetAllNavigationWithCoroutine(CsvConvertUtil.csvToArrayList(file)) }
    }

    @PostMapping("1")
    fun resetNavigationWithCsvNoCoroutine(@RequestParam("file") file: MultipartFile): List<Navigation> {
        return navigationService.resetAllNavigation(CsvConvertUtil.csvToArrayList(file))
    }
}