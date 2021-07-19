package com.maxpilotto.krand.extensions

import com.maxpilotto.krand.utils.Picker
import com.maxpilotto.krand.utils.Shuffler

fun <K,V> Map<K,V>.pickOne(): Map.Entry<K,V> {
    return Picker().one(this)
}

fun <K,V> Map<K,V>.pickMany(count: Int): List<Map.Entry<K,V>> {
    return Picker().many(this, count)
}

fun <K,V> Map<K,V>.pickWeighted(weights: Iterable<Int>): Map.Entry<K,V> {
    return Picker().weighted(this, weights)
}

fun <K,V> Map<K,V>.pickWeighted(weights: Iterable<Int>, count: Int): List<Map.Entry<K,V>> {
    return Picker().weighted(this, weights, count)
}

fun <K,V> Map<K,V>.shuffle(): Map<K,V> {
    return Shuffler()(this)
}