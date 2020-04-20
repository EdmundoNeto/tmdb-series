package com.edmundo.tmdb.view

import com.edmundo.tmdb.utils.toDate
import com.edmundo.tmdb.utils.toDateString
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.LocalDate
import java.util.*

class ViewUtilsTest {

    @Test
    fun `string date should return a date`() {

        val stringDate = "2020-01-01"
        val actual = stringDate.toDate()?.toDateString()

        val expectedDate = "01/01/2020"
        Assert.assertEquals(expectedDate, actual)
    }

}