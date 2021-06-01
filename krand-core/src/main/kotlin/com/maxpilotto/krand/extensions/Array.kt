package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Pick

fun <T> Array<T>.pickOne(seed: Any? = null): T {
    return Pick.one(this, seed)
}

fun <T> Array<T>.pickMany(count: Int, seed: Any? = null): List<T> {
    return Pick.many(this, count, seed)
}

fun <T> Array<T>.pickWeighted(weights: Iterable<Int>, seed: Any? = null): T {
    return Pick.weighted(this, weights, seed)
}

fun <T> Array<T>.pickWeighted(weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
    return Pick.weighted(this, weights, count, seed)
}

internal inline fun <reified T> Array<out T>.toTypedArray(): Array<T> {
    return Array(size) {
        get(it)
    }
}
