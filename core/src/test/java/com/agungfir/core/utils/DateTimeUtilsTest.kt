package com.agungfir.core.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DateTimeUtilsTest {

    @Test
    fun `given incorrect current dd-MM-yyyy format then should return current date input correctly`(){
        val currentDate = "false"
        assertEquals("10-08-2022", DateTimeUtils.formatDate(currentDate))
    }

    @Test
    fun `given correct current yyyy-MM-dd format then should return format dd MMM yyyy correctly`() {
        val currentDate = "2022-08-10"
        assertEquals("10 Aug 2022", DateTimeUtils.formatDate(currentDate))
    }

    @Test
    fun `given correct current yyyy-MM-dd format then should return format dd MM yyyy correctly`() {
        val currentDate = "2022-08-10"
        assertEquals("10 August 2022", DateTimeUtils.formatLongDate(currentDate))
    }
}