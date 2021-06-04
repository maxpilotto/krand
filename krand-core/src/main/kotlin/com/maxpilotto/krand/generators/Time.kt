package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Timezone
import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("ampm", String::class)
interface _AMPMGenerator

@Generator("date", Date::class)
interface _DateGenerator {
    val year: String
    val month: String
    val day: String
    val string: Boolean
}

@Generator("hammertime", Int::class)
interface _HammertimeGenerator

@Generator("hour", Int::class)
interface _HourGenerator {
    val twentyFour: Boolean
}

@Generator("millisecond", Int::class)
interface _MillisecondGenerator

@Generator("minute", Int::class)
interface _MinuteGenerator

@Generator("month", String::class)
interface _MonthGenerator   //TODO: Month object

@Generator("second", Int::class)
interface _SecondGenerator

@Generator("timestamp", Int::class)
interface _TimestampGenerator

@Generator("timezone", Timezone::class)
interface _TimezoneGenerator

@Generator("weekday", String::class)
interface _WeekdayGenerator {
    val weekdayOnly: Boolean
}

@Generator("year", String::class)
interface _YearGenerator {
    val min: String
    val max: String
}