package com.agungfir.core.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtils {

    fun formatDate(currentDateString: String): String {
        return try {
            val instant = Instant.parse(currentDateString + "T00:00:00.732111Z")
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                .withZone(ZoneId.of(TimeZone.getDefault().id))
            formatter.format(instant)
        } catch (e: Exception) {
            currentDateString
        }
    }

    fun formatLongDate(currentDateString: String): String {
        return try {
            val instant = Instant.parse(currentDateString + "T00:00:00.732111Z")
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                .withZone(ZoneId.of(TimeZone.getDefault().id))
            formatter.format(instant)
        } catch (e: Exception) {
            currentDateString
        }
    }
}