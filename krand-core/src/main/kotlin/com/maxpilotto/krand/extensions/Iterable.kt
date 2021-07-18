package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Picker

fun <T> Iterable<T>.pickOne(): T {
    return Picker().one(this)
}

fun <T> Iterable<T>.pickMany(count: Int): List<T> {
    return Picker().many(this, count)
}

fun <T> Iterable<T>.pickWeighted(weights: Iterable<Int>): T {
    return Picker().weighted(this, weights)
}

fun <T> Iterable<T>.pickWeighted(weights: Iterable<Int>, count: Int): List<T> {
    return Picker().weighted(this, weights, count)
}