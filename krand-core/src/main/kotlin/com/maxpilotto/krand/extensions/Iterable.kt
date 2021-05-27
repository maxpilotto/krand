package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Pick

fun <T> Iterable<T>.pickOne(seed: Any? = null): T {
    return Pick.one(this, seed)
}

fun <T> Iterable<T>.pickMany(count: Int, seed: Any? = null): List<T> {
    return Pick.many(this, count, seed)
}

fun <T> Iterable<T>.pickWeighted(weights: Iterable<Int>, seed: Any? = null): T {
    return Pick.weighted(this, weights, seed)
}

fun <T> Iterable<T>.pickWeighted(weights: Iterable<Int>, count: Int, seed: Any? = null): List<T> {
    return Pick.weighted(this, weights, count, seed)
}