package com.example.navigationreset.util

import com.example.navigationreset.domain.Navigation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpHeaders


@ExtendWith(MockitoExtension::class)
internal class RequestUtilTest {
    @Test
    fun `USER-AGENT header를 포함한 GET Request를 던진다`() {
        // given
        val testNavigation = Navigation("01012345678")

        // when
        val request = RequestUtil.createResetRequest(testNavigation)

        // then
        assertTrue(request.containsHeader(HttpHeaders.USER_AGENT))
    }
}