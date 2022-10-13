package com.example.navigationreset.util

import com.example.navigationreset.domain.Navigation
import org.springframework.web.multipart.MultipartFile

object CsvConvertUtil {
    fun csvToArrayList(file: MultipartFile): ArrayList<Navigation> {
        val reader = file.inputStream.bufferedReader()
        reader.readLine() // 머리를 빼는 역할

        val result = reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val id = it.replace(",", "")
                require(!containsRegax(id)) { throw RuntimeException() }
                Navigation(id)
            }.toList()

        return ArrayList(result)
    }

    private fun containsRegax(element: String) = element.contains(",")
}