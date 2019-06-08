package com.projects.pupus.schichtbericht.DatenTypen

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Moment(var date: LocalDateTime, var ort: String) {

    val patternDay = "dd.MM.yyyy"
    val patternTime = "HH:mm"

    fun getTime(): String {
        return date.format(DateTimeFormatter.ofPattern(patternTime))
    }

    fun getDay(): String {
        return date.format(DateTimeFormatter.ofPattern(patternDay))
    }
}