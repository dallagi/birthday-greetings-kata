package it.xpug.kata.birthday_greetings

import org.junit.Assert.assertTrue
import org.junit.Test

class GreetingShould {
    @Test
    fun have_customized_body_for_employee() {
        val employee = Employee("John", "Doe", "1990/01/01", "john@doe.com")

        assertTrue(Greeting(employee).body().contains(employee.firstName))
    }
}