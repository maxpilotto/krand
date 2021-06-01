package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("address", String::class)
interface _AddressGenerator {
    val shortSuffix: Boolean
}

@Generator("altitude", Float::class)
interface _AltitudeGenerator {
    val fixed: Int
    val max: Int
}

@Generator("areacode", String::class)
interface _AreacodeGenerator

@Generator("city", String::class)
interface _CityGenerator

@Generator("coordinates", String::class)
interface _CoordinatesGenerator {
    val fixed: Int
}

@Generator("country", String::class)
interface _CountryGenerator {
    val full: Boolean
}

@Generator("depth", Float::class)
interface _DepthGenerator {
    val fixed: Int
    val min: Int
}

@Generator("geohash", String::class)
interface _GeohashGenerator {
    val length: Int
}

@Generator("latitude", Float::class)
interface _LatitudeGenerator {
    val fixed: Int
    val min: Float
    val max: Float
}

@Generator("locale", String::class)
interface _LocaleGenerator {
    val region: Boolean
}

@Generator("longitude", Float::class)
interface _LongitudeGenerator {
    val fixed: Int
    val min: Float
    val max: Float
}

@Generator("phone", String::class)
interface _PhoneGenerator {
    val formatted: Boolean
    val country: String    //'us', 'uk', or 'fr'
}

@Generator("postal", String::class)
interface _PostalGenerator

@Generator("postcode", String::class)
interface _PostcodeGenerator

@Generator("province", String::class)
interface _ProvinceGenerator {
    val full: Boolean
}

@Generator("state", String::class)
interface _StateGenerator {
    val full: Boolean
    val territories: Boolean
    val armedForces: Boolean
    val country: String
}

@Generator("street", String::class)
interface _StreetGenerator {
    val country: String //us, it
    val shortSuffix: Boolean
    val syllables: Int
}

@Generator("zip", String::class)
interface _ZipCodeGenerator {
    val plusFour: Boolean
}