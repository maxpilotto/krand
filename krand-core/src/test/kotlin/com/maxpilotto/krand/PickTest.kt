package com.maxpilotto.krand

import com.maxpilotto.krand.utils.Pick
import org.junit.jupiter.api.Test

class PickTest {
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
    private val string = "helloworld1234"

    @Test
    fun pickWeightedMany() {
        assert(
            Pick.weighted(companies, weights, 10).all { it == "Microsoft" }
        )
    }

    @Test
    fun pickWeightedOne() {
        assert(
            Pick.weighted(companies, weights) == "Microsoft"
        )
    }

    @Test
    fun pickWeightedEnumMany() {
        assert(
            Pick.weighted<Animal>(weights, 10).all { it == Animal.Fox }
        )
    }

    @Test
    fun pickWeightedEnumOne() {
        assert(
            Pick.weighted<Animal>(weights) == Animal.Fox
        )
    }

    @Test
    fun pickOne() {
        assert(
            Pick.one(companies) in companies
        )
    }

    @Test
    fun pickOneEnum() {
        assert(
            Pick.one<Animal>() in Animal.values()
        )
    }

    @Test
    fun pickMany() {
        assert(
            Pick.many(companies, 20).all { it in companies }
        )
    }

    @Test
    fun pickManyEnum() {
        assert(
            Pick.many<Animal>(20).all { it in Animal.values() }
        )
    }

    @Test
    fun pickManyWithSeed() {
        val list1 = Pick.many(companies, 20, seed)
        val list2 = Pick.many(companies, 20, seed)

        list1.forEachIndexed { i, item ->
            assert(item == list2[i])
        }
    }

    @Test
    fun pickManyEnumWithSeed() {
        val list1 = Pick.many<Animal>(20, seed)
        val list2 = Pick.many<Animal>(20, seed)

        list1.forEachIndexed { i, item ->
            assert(item == list2[i])
        }
    }

    @Test
    fun oneCharacter() {
        assert(Pick.one(string) in string)
    }

    @Test
    fun manyCharacters() {
        assert(Pick.many(string, 20).all { it in string })
    }
}