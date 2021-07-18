package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Picker

fun <T> Array<T>.pickOne(): T {
    return Picker().one(this)
}

fun <T> Array<T>.pickMany(count: Int): List<T> {
    return Picker().many(this, count)
}

fun <T> Array<T>.pickWeighted(weights: Iterable<Int>): T {
    return Picker().weighted(this, weights)
}

fun <T> Array<T>.pickWeighted(weights: Iterable<Int>, count: Int): List<T> {
    return Picker().weighted(this, weights, count)
}

internal inline fun <reified T> Array<out T>.toTypedArray(): Array<T> {
    return Array(size) {
        get(it)
    }
}
