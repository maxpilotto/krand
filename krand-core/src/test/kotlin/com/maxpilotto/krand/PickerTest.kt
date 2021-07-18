package com.maxpilotto.krand

import com.maxpilotto.krand.utils.Picker
import org.junit.jupiter.api.Test

class PickerTest {
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
    private val string = "helloworld1234"
    private val seed = "MySeed"

    @Test
    fun weightedMany() {
        assert(
            Picker().weighted(companies, weights, 10).all { it == "Microsoft" }
        )
    }

    @Test
    fun weightedOne() {
        assert(
            Picker().weighted(companies, weights) == "Microsoft"
        )
    }

    @Test
    fun weightedManyEnum() {
        assert(
            Picker().weighted<Animal>(weights, 10).all { it == Animal.Fox }
        )
    }

    @Test
    fun weightedOneEnum() {
        assert(
            Picker().weighted<Animal>(weights) == Animal.Fox
        )
    }

    @Test
    fun one() {
        assert(
            Picker().one(companies) in companies
        )
    }

    @Test
    fun oneEnum() {
        assert(
            Picker().one<Animal>() in Animal.values()
        )
    }

    @Test
    fun many() {
        assert(
            Picker().many(companies, 20).all { it in companies }
        )
    }

    @Test
    fun manyEnum() {
        assert(
            Picker().many<Animal>(20).all { it in Animal.values() }
        )
    }

    @Test
    fun oneCharacter() {
        assert(Picker().one(string) in string)
    }

    @Test
    fun manyCharacters() {
        assert(Picker().many(string, 20).all { it in string })
    }

    @Test
    fun oneSeed() {
        assert(
            Picker(seed).one(companies) == Picker(seed).one(companies)
        )
    }

    @Test
    fun manySeed() {
        assert(
            Picker(seed).many(companies, 10) == Picker(seed).many(companies, 10)
        )
    }

    @Test
    fun manyEnumSeed() {
        assert(
            Picker(seed).many<Animal>( 10) == Picker(seed).many<PickerTest.Animal>( 10)
        )
    }

    @Test
    fun weightedSeed() {
        assert(
            Picker(seed).weighted(companies, weights) == Picker(seed).weighted(companies, weights)
        )
    }
}

