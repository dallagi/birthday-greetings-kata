package it.xpug.kata.birthday_greetings

import org.junit.Assert.*
import org.junit.Test
import org.junit.runners.model.MultipleFailureException.assertEmpty
import java.io.File

class FileEmployeesShould {
    companion object {
        private const val HEADER = "last_name, first_name, date_of_birth, email"
    }

    @Test
    fun always_skip_header() {
        val filePath = createTempFile(listOf(HEADER))

        val employees = FileEmployees(filePath).withBirthdayOn(XDate())

        assertEquals(0, employees.count())
    }

    @Test
    fun return_an_employee_for_each_row_when_employees_birthday_matches_date() {
        val filePath = createTempFile(listOf(
            HEADER,
            "Birthday, Guy, 1990/01/01, birthday@guy.com",
            "Normalday, Gal, 1999/02/01, yet@another.day"
        ))

        val employees = FileEmployees(filePath).withBirthdayOn(XDate("2019/01/01"))

        assertEquals(1, employees.count())
        assertEquals(Employee("Guy", "Birthday", "1990/01/01", "birthday@guy.com"), employees.first())
    }

    private fun createTempFile(lines: List<String>): String {
        val tempFile = File.createTempFile("BirthdayGreetingTest-", ".tmp")
        tempFile.writeText(lines.joinToString("\n"))
        return tempFile.absolutePath
    }
}