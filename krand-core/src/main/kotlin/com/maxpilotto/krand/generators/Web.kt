package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("avatar", String::class)
interface _AvatarGenerator {
    val protocol: String
    val fileExtension: String
    val email: String
}

@Generator("color", String::class)
interface _ColorGenerator {
    val format: String
    val grayscale: Boolean
    val casing: String
}

@Generator("company", String::class)
interface _CompanyGenerator

@Generator("domain", String::class)
interface _DomainGenerator {
    val tld: String
}

@Generator("email", String::class)
interface _EmailGenerator {
    val domain: String
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
    val rank: Boolean
}

@Generator("tld", String::class)
interface _TopLevelDomainGenerator

@Generator("twitter", String::class)
interface _TwitterGenerator

@Generator("url", String::class)
interface _URLGenerator {
    val protocol: String
    val domain: String
    val prefix: String
    val path: String
    val extensions: Array<String>
}