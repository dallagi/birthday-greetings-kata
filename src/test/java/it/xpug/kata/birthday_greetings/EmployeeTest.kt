package it.xpug.kata.birthday_greetings

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class EmployeeTest {

    @Test
    fun testBirthday() {
        val employee = Employee("foo", "bar", "1990/01/31", "a@b.c")
        assertFalse("not his birthday", employee.isBirthday(XDate("2008/01/30")))
        assertTrue("his birthday", employee.isBirthday(XDate("2008/01/31")))
    }

    @Test
    fun equality() {
        val base = Employee("First", "Last", "1999/09/01", "first@last.com")
        val same = Employee("First", "Last", "1999/09/01", "first@last.com")
        val different = Employee("First", "Last", "1999/09/01", "boom@boom.com")

        assertTrue(base == same)
        assertFalse(base == different)
    }
}
