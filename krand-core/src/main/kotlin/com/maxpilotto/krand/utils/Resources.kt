package com.maxpilotto.krand.utils

import java.io.InputStream

internal object Resources {
    fun load(path: String): InputStream {
        return Resources::class.java.getResourceAsStream(path) ?: throw Exception("Cannot open file $path")
    }
}