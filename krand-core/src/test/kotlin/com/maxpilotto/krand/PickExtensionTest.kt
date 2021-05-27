package com.maxpilotto.krand

import com.maxpilotto.krand.extensions.*
import com.maxpilotto.krand.utils.Pick
import org.junit.jupiter.api.Test


class PickExtensionTest {
    enum class Animal {
        Dog,
        Cat,
        Fox
    }

    private val companies = listOf(
        "Apple", "Google", "Microsoft"
    )
    private val weights = listOf(
        0, 0, 100
    )
    private val seed = 123456789

    @Test
    fun pickWeightedMany() {
        assert(
            companies.pickWeighted(weights, 10).all { it == "Microsoft" }
        )
    }

    @Test
    fun pickWeightedOne() {
        assert(
            companies.pickWeighted(weights) == "Microsoft"
        )
    }

    @Test
    fun pickWeightedEnumMany() {
        assert(
            Animal.values().pickWeighted(weights, 10).all { it == Animal.Fox }
        )
    }

    @Test
    fun pickWeightedEnumOne() {
        assert(
            Animal.values().pickWeighted(weights) == Animal.Fox
        )
    }

    @Test
    fun pickOne() {
        assert(
            companies.pickOne() in companies
        )
    }

    @Test
    fun pickOneEnum() {
        assert(
            Animal.values().pickOne() in Animal.values()
        )
    }

    @Test
    fun pickMany() {
        assert(
            companies.pickMany(20).all { it in companies }
        )
    }

    @Test
    fun pickManyEnum() {
        assert(
            Animal.values().pickMany(20).all { it in Animal.values() }
        )
    }

    @Test
    fun pickManyWithSeed() {
        val list1 = companies.pickMany(20, seed)
        val list2 = companies.pickMany(20, seed)

        list1.forEachIndexed { i, item ->
            assert(item == list2[i])
        }
    }

    @Test
    fun pickManyEnumWithSeed() {
        val list1 = Animal.values().pickMany(20, seed)
        val list2 = Animal.values().pickMany(20, seed)

        list1.forEachIndexed { i, item ->
            assert(item == list2[i])
        }
    }
}