package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("address", String::class)
interface _AddressGenerator {
    fun gen(
        shortSuffix: Boolean = true
    )
}

@Generator("altitude", Float::class)
interface _AltitudeGenerator {
    fun gen(
        fixed: Int = 5,
        max: Int = 8848
    )
}

@Generator("areacode", String::class)
interface _AreacodeGenerator

@Generator("city", String::class)
interface _CityGenerator

@Generator("coordinates", String::class)
interface _CoordinatesGenerator {
    fun gen(
        fixed: Int = 5
    )
}

@Generator("country", String::class)
interface _CountryGenerator {
    fun gen(
        full: Boolean = true
    )
}

@Generator("depth", Float::class)
interface _DepthGenerator {
    fun gen(
        fixed: Int = 5,
        min: Int = -2550
    )
}

@Generator("geohash", String::class)
interface _GeohashGenerator {
    fun gen(
        length: Int = 7
    )
}

@Generator("latitude", Float::class)
interface _LatitudeGenerator {
    fun gen(
        fixed: Int = 5,
        min: Float = -90F,
        max: Float = 90F
    )
}

@Generator("locale", String::class)
interface _LocaleGenerator {
    fun gen(
        region: Boolean = true
    )
}

@Generator("longitude", Float::class)
interface _LongitudeGenerator {
    fun gen(
        fixed: Int = 5,
        min: Float = -180F,
        max: Float = 180F
    )
}

@Generator("phone", String::class)
interface _PhoneGenerator {
    fun gen(
        formatted: Boolean = false,
        country: String = ""    //'us', 'uk', or 'fr'
    )
}

@Generator("postal", String::class)
interface _PostalGenerator

@Generator("postcode", String::class)
interface _PostcodeGenerator

@Generator("province", String::class)
interface _ProvinceGenerator {
    fun gen(
        full: Boolean = true
    )
}

@Generator("state", String::class)
interface _StateGenerator {
    fun gen(
        full: Boolean = true,
        territories: Boolean = true,
        armedForces: Boolean = true,
        country: String = ""
    )
}

@Generator("street", String::class)
interface _StreetGenerator {
    fun gen(
        country: String = "", //us, it
        shortSuffix: Boolean = true,
        syllables: Int = 8
    )
}

@Generator("zip", String::class)
interface _ZipCodeGenerator {
    fun gen(
        plusFour: Boolean = true
    )
}