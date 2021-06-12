package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("avatar", String::class)
internal interface _AvatarGenerator {
    val protocol: String
    val fileExtension: String
    val email: String
}

@Generator("color", String::class)
internal interface _ColorGenerator {
    val format: String
    val grayscale: Boolean
    val casing: String
}

@Generator("company", String::class)
internal interface _CompanyGenerator

@Generator("domain", String::class)
internal interface _DomainGenerator {
    val tld: String
}

@Generator("email", String::class)
internal interface _EmailGenerator {
    val domain: String
}

@Generator("fbid", String::class)
internal interface _FacebookIDGenerator

@Generator("google_analytics", String::class)
internal interface _GoogleAnalyticsGenerator

@Generator("hashtag", String::class)
internal interface _HashtagGenerator

@Generator("ip", String::class)
internal interface _IPGenerator

@Generator("ipv6", String::class)
internal interface _IPV6Generator

@Generator("klout", Double::class)
internal interface _KloutScoreGenerator

@Generator("profession", String::class)
internal interface _ProfessionGenerator {
    val rank: Boolean
}

@Generator("tld", String::class)
internal interface _TopLevelDomainGenerator

@Generator("twitter", String::class)
internal interface _TwitterGenerator

@Generator("url", String::class)
internal interface _URLGenerator {
    val protocol: String
    val domain: String
    val prefix: String
    val path: String
    val extensions: Array<String>
}