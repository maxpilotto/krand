package com.maxpilotto.krand.models

data class Timezone(
    val name: String,
    val abbr: String,
    val offset: Double,
    val isdst: Boolean,
    val text: String,
    val utc: List<String>
)