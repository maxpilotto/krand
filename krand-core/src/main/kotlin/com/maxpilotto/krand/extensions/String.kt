package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Picker

fun String.pickOne(): String {
    return Picker().one(this)
}

fun String.pickMany(count: Int): String {
    return Picker().many(this, count)
}

fun String.pickChar(): Char {
    return Picker().one(toList())
}

fun String.pickChars(count: Int): List<Char> {
    return Picker().many(toList(), count)
}