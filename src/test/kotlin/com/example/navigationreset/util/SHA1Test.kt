package com.example.navigationreset.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class SHA1Test {

    @Test
    fun `라우터 번호를 줬을 때, checksum의 길이가 40이어야 한다`() {
        // given
        val testRouterId = "01012345678"

        // when
        val actualChecksum = SHA1.encryptString(testRouterId)

        // then
        assertEquals(40, actualChecksum.length)
    }
}