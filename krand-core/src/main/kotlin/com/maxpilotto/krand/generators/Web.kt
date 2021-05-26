package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("avatar", String::class)
interface _AvatarGenerator {
    fun gen(
        protocol: String = "https",
        fileExtension: String = "jpg",
        email: String = ""
    )
}

@Generator("color", String::class)
interface _ColorGenerator {
    fun gen(
        format: String = "hex",
        grayscale: Boolean = true,
        casing: String = "upper"
    )
}

@Generator("company", String::class)
interface _CompanyGenerator

@Generator("domain", String::class)
interface _DomainGenerator {
    fun gen(
        tld: String = "com"
    )
}

@Generator("email", String::class)
interface _EmailGenerator {
    fun gen(
        domain: String = ""
    )
}

@Generator("fbid", String::class)
interface _FacebookIDGenerator

@Generator("google_analytics", String::class)
interface _GoogleAnalyticsGenerator

@Generator("hashtag", String::class)
interface _HashtagGenerator

@Generator("ip", String::class)
interface _IPGenerator

@Generator("ipv6", String::class)
interface _IPV6Generator

@Generator("klout", Double::class)
interface _KloutScoreGenerator

@Generator("profession", String::class)
interface _ProfessionGenerator {
    fun gen(
        rank: Boolean = true
    )
}

@Generator("tld", String::class)
interface _TopLevelDomainGenerator

@Generator("twitter", String::class)
interface _TwitterGenerator

@Generator("url", String::class)
interface _URLGenerator {
    fun gen(
        protocol: String = "",
        domain: String = "",
        prefix: String = "",
        path: String = "",
        extensions: Array<String> = arrayOf()
    )
}