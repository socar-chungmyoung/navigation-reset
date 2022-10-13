package com.example.navigationreset.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NavigationTest {

    @Test
    fun `-가 들어간 라우터 번호를 - 없이 들어갈 수 있도록 한다`() {
        // given
        val testRouterId = "010-1234-5678"

        // when
        val navigation = Navigation(testRouterId)

        // then
        assertFalse(navigation.routerId.contains("-"))
    }

    @Test
    fun `-가 없는 라우터 번호도 올바르게 들어갈 수 있도록 한다`() {
        // given
        val testRouterId = "01012345678"

        // when
        val navigation = Navigation(testRouterId)

        // then
        assertFalse(navigation.routerId.contains("-"))
    }

    @Test
    fun `navigationStatus가 1이 아니면 false를 리턴한다`() {
        // given
        val testNavigation = Navigation("01012345678", "0")

        // when + then
        assertFalse(testNavigation.isResetCompleted())
    }

    @Test
    fun `navigationStatus가 1이면 true를 리턴한다`() {
        // given
        val testNavigation = Navigation("01012345678", "1")

        // when + then
        assertTrue(testNavigation.isResetCompleted())
    }
}