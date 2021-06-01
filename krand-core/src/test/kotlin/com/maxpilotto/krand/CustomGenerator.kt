package com.maxpilotto.krand

import com.maxpilotto.krand.models.AbstractGenerator

class CustomGenerator : AbstractGenerator<Int>() {
    var isOdd: Boolean = false
        private set

    override fun one(): Int {
        //ChanceJS generator name
        val number = execute<Int>("integer")

        return if (isOdd) {
            if (number % 2 != 0) number else number - 1
        } else {
            if (number % 2 == 0) number else number - 1
        }
    }

    fun isOdd(isOdd: Boolean) = apply {
        this.isOdd = isOdd
    }
}