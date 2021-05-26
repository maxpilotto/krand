package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Pick

fun <T> Iterable<T>.pickOne(seed: Any? = null): T {
    return Pick.one(this, seed)
}

fun <T> Iterable<T>.pickSet(count: Int, seed: Any? = null): Set<T> {
    return Pick.set(this, count, seed)
}

fun <T> Iterable<T>.pickWeighted(weights: Iterable<Int>, seed: Any? = null): T {
    return Pick.weighted(this, weights, seed)
}

fun <T> Iterable<T>.pickWeightedSet(weights: Iterable<Int>, count: Int, seed: Any? = null): Set<T> {
    return Pick.weightedSet(this, weights, count, seed)
}