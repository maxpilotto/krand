package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("address", String::class)
internal interface _AddressGenerator {
    val shortSuffix: Boolean
}

@Generator("altitude", Float::class)
internal interface _AltitudeGenerator {
    val fixed: Int
    val max: Int
}

@Generator("areacode", String::class)
internal interface _AreacodeGenerator

@Generator("city", String::class)
internal interface _CityGenerator

@Generator("coordinates", String::class)
internal interface _CoordinatesGenerator {
    val fixed: Int
    val format: String
}

@Generator("country", String::class)
internal interface _CountryGenerator {
    val full: Boolean
}

@Generator("depth", Float::class)
internal interface _DepthGenerator {
    val fixed: Int
    val min: Int
}

@Generator("geohash", String::class)
internal interface _GeohashGenerator {
    val length: Int
}

@Generator("latitude", Float::class)
internal interface _LatitudeGenerator {
    val fixed: Int
    val min: Float
    val max: Float
    val format: String
}

@Generator("locale", String::class)
internal interface _LocaleGenerator {
    val region: Boolean
}

@Generator("longitude", Float::class)
internal interface _LongitudeGenerator {
    val fixed: Int
    val min: Float
    val max: Float
    val format: String
}

@Generator("phone", String::class)
internal interface _PhoneGenerator {
    val formatted: Boolean
    val country: String    //'us', 'uk', or 'fr'
}

@Generator("postal", String::class)
internal interface _PostalGenerator

@Generator("postcode", String::class)
internal interface _PostcodeGenerator

@Generator("province", String::class)
internal interface _ProvinceGenerator {
    val full: Boolean
    val country: String     //'ca', 'it'
}

@Generator("state", String::class)
internal interface _StateGenerator {
    val full: Boolean
    val territories: Boolean
    val armedForces: Boolean
    val USandDC: Boolean
    val country: String
}

@Generator("street", String::class)
internal interface _StreetGenerator {
    val country: String //us, it
    val shortSuffix: Boolean
    val syllables: Int
}

@Generator("zip", String::class)
internal interface _ZipCodeGenerator {
    val plusFour: Boolean
}