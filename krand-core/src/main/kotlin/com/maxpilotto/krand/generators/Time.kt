package com.maxpilotto.krand.generators

import com.maxpilotto.krand.models.Timezone
import com.maxpilotto.krand.processor.annotations.Generator
import java.util.*

@Generator("ampm", String::class)
internal interface _AMPMGenerator

@Generator("date", Date::class)
internal interface _DateGenerator {
    val year: String
    val month: String
    val day: String
    val string: Boolean
}

@Generator("hammertime", Int::class)
internal interface _HammertimeGenerator

@Generator("hour", Int::class)
internal interface _HourGenerator {
    val twentyFour: Boolean
}

@Generator("millisecond", Int::class)
internal interface _MillisecondGenerator

@Generator("minute", Int::class)
internal interface _MinuteGenerator

@Generator("month", String::class)
internal interface _MonthGenerator   //TODO: Month object

@Generator("second", Int::class)
internal interface _SecondGenerator

@Generator("timestamp", Int::class)
internal interface _TimestampGenerator

@Generator("timezone", Timezone::class)
internal interface _TimezoneGenerator

@Generator("weekday", String::class)
internal interface _WeekdayGenerator {
    val weekdayOnly: Boolean
}

@Generator("year", String::class)
internal interface _YearGenerator {
    val min: String
    val max: String
}