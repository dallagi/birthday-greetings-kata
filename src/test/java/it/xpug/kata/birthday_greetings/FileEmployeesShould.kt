package it.xpug.kata.birthday_greetings

import org.junit.Assert.*
import org.junit.Test

class FileEmployeesShould {
    @Test
    fun return_an_employee_for_each_row_except_header() {
        val employees = FileEmployees("employee_data").all()

        assertEquals(employees, sequenceOf(
            Employee("Doe", "John", "1982/10/08", "john.doe@foobar.com"),
            Employee("Ann", "Mary", "1975/03/11", "mary.ann@foobar.com")
        ))
    }
}