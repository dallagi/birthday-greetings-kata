package it.xpug.kata.birthday_greetings

import org.junit.Assert.*
import org.junit.*


class XDateShould {
    @Test
    fun provide_getters_for_day_and_month() {
        val date = XDate("1789/01/24")
        assertEquals(1, date.month.toLong())
        assertEquals(24, date.day.toLong())
    }

    @Test
    fun check_equality_of_day_and_month() {
        val date = XDate("1789/01/24")
        val sameDay = XDate("2001/01/24")
        val notSameDay = XDate("1789/01/25")
        val notSameMonth = XDate("1789/02/25")

        assertTrue("same", date.isSameDay(sameDay))
        assertFalse("not same day", date.isSameDay(notSameDay))
        assertFalse("not same month", date.isSameDay(notSameMonth))
    }

    @Test
    fun support_equality_of_date() {
        val base = XDate("2000/01/02")
        val same = XDate("2000/01/02")
        val different = XDate("2000/01/04")

        assertTrue(base == base)
        assertTrue(base == same)
        assertFalse(base == different)
    }

}
