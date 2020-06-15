package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits) :Date {
    var time =this.time
    time+= when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff() : String{
    val diff = Date().time - this.time
    val seconds = diff/1000
    val minutes = seconds/60
    val hours = minutes/60
    val days = hours/24
    val stringDiff: String

    if (seconds >= 0) {
        if (seconds <= 1) stringDiff = "только что"
        else if (seconds <= 45) stringDiff = "несколько секунд назад"
        else if (seconds <= 75) stringDiff = "минуту назад"
        else if (minutes <= 45) stringDiff = "${TimeUnits.MINUTE.plural(minutes.toInt())} назад"
        else if (minutes <= 75) stringDiff = "час назад"
        else if (hours <= 22) stringDiff = "${TimeUnits.HOUR.plural(hours.toInt())} назад"
        else if (hours <= 26) stringDiff = "день назад"
        else if (days <= 360) stringDiff = "${TimeUnits.DAY.plural(days.toInt())} назад"
        else stringDiff = "больше года назад"
    } else {
        if (-seconds <= 1) stringDiff = "прямо сейчас"
        else if (-seconds <= 45) stringDiff = "через несколько секунд"
        else if (-seconds <= 75) stringDiff = "через минуту"
        else if (-minutes <= 45) stringDiff = "через ${TimeUnits.MINUTE.plural(-minutes.toInt())}"
        else if (-minutes <= 75) stringDiff = "через час"
        else if (-hours <= 22) stringDiff = "через ${TimeUnits.HOUR.plural(-hours.toInt())}"
        else if (-hours <= 26) stringDiff = "через день"
        else if (-days <= 360) stringDiff = "через ${TimeUnits.DAY.plural(-days.toInt())}"
        else stringDiff = "больше чем через год"
    }
    return stringDiff
}

enum class TimeUnits {
    SECOND{
        override fun plural(value: Int): String {
            val lastCypher = abs(value)%10
            val stringUnits = when {
                lastCypher == 1 -> "$value секунду"
                listOf(2, 3, 4).contains(lastCypher) -> "$value секунды"
                else -> "$value секунд"
            }
            return stringUnits
        }
    },
    MINUTE{
        override fun plural(value: Int): String {
            val lastCypher = abs(value)%10
            val stringUnits = when {
                lastCypher == 1 -> "$value минуту"
                listOf(2, 3, 4).contains(lastCypher) -> "$value минуты"
                else -> "$value минут"
            }
            return stringUnits
        }
    },
    HOUR{
        override fun plural(value: Int): String {
            val lastCypher = abs(value)%10
            val stringUnits = when {
                lastCypher == 1 -> "$value час"
                listOf(2, 3, 4).contains(lastCypher) -> "$value часа"
                else -> "$value часов"
            }
            return stringUnits
        }
    },
    DAY{
        override fun plural(value: Int): String {
            val lastCypher = abs(value)%10
            val stringUnits = when {
                lastCypher == 1 -> "$value день"
                listOf(2, 3, 4).contains(lastCypher) -> "$value дня"
                else -> "$value дней"
            }
            return stringUnits
        }
    };

    open fun plural(value: Int): String = ""

}

