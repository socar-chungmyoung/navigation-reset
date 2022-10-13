package com.example.navigationreset.service

import com.example.navigationreset.domain.Navigation
import com.example.navigationreset.util.JSONUtil
import com.example.navigationreset.util.RequestUtil
import io.mockk.*
import org.apache.http.HttpResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class NavigationServiceTest {

    @InjectMocks
    lateinit var navigationService: NavigationService

    @BeforeEach
    fun beforeTest() {
        mockkObject(RequestUtil)
        mockkObject(JSONUtil)
    }

    @AfterEach
    fun afterTest() {
        unmockkAll()
    }

    @Test
    fun `던진 navigation의 status가 변경되어야 한다`() {
        // given
        val testNavigation = Navigation("01012345678")
        val mockResponse = mockk<HttpResponse>()
        every { RequestUtil.sendGetRequest(any()) } returns mockResponse
        every { JSONUtil.extractCodeAtResponse(mockResponse) } returns "0"

        // when
        val actualNavigation = navigationService.resetNavigation(testNavigation)

        // then
        Assertions.assertTrue(actualNavigation.navigationStatus.isNotBlank())
    }

    @Test
    fun `모든 navigation의 status가 변경되어야 한다`() {
        // given
        val mockResponse = mockk<HttpResponse>()
        every { RequestUtil.sendGetRequest(any()) } returns mockResponse
        every { JSONUtil.extractCodeAtResponse(mockResponse) } returns "0"

        // when
        val actualNavigation = navigationService.resetAllNavigation(listOf(Navigation("01000000000"), Navigation("01000000001")))

        // then
        actualNavigation.forEach {
            Assertions.assertTrue(it.navigationStatus.isNotBlank())
        }
    }
}