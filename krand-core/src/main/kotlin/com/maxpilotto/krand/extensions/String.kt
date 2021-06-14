package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Pick

fun String.pickOne(seed: Any? = null): String {
    return Pick.one(this, seed)
}

fun String.pickMany(count: Int, seed: Any? = null): String {
    return Pick.many(this, count, seed)
}

fun String.pickChar(seed: Any? = null): Char {
    return Pick.one(toList(), seed)
}

fun String.pickChars(count: Int, seed: Any? = null): List<Char> {
    return Pick.many(toList(), count, seed)
}