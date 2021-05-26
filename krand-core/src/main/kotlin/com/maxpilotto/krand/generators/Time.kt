package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Timezone
import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("ampm", String::class)
interface _AMPMGenerator

@Generator("date", Date::class)
interface _DateGenerator {
    fun gen(
        year: String = "",
        month: String = "",
        day: String = "",
        string: Boolean = true
    )
}

@Generator("hammertime", Int::class)
interface _HammertimeGenerator

@Generator("hour", Int::class)
interface _HourGenerator {
    fun gen(
        twentyFour: Boolean = true
    )
}

@Generator("millisecond", Int::class)
interface _MillisecondGenerator

@Generator("minute", Int::class)
interface _MinuteGenerator

@Generator("month", String::class)
interface _MonthGenerator

@Generator("second", Int::class)
interface _SecondGenerator

@Generator("timestamp", Int::class)
interface _TimestampGenerator

@Generator("timezone", Timezone::class)
interface _TimezoneGenerator

@Generator("weekday", String::class)
interface _WeekdayGenerator {
    fun gen(
        weekdayOnly: Boolean = false
    )
}

@Generator("year", String::class)
interface _YearGenerator {
    fun gen(
        min: String = "",
        max: String = ""
    )
}

