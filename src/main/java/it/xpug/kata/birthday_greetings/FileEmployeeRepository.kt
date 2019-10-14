package it.xpug.kata.birthday_greetings

import java.io.File

class FileEmployeeRepository(private val fileName: String) : EmployeeRepository {

    override fun all(): List<Employee> {
        return File(fileName).readLines()
            .toMutableList()
            .also(removeHeadLine())
            .toList()
            .map(asEmployee())
    }

    private fun asEmployee(): (String) -> Employee {
        return { line ->
            val (lastName, firstName, birthDate, email) = line.split(", ").toTypedArray()

            Employee(firstName, lastName, birthDate, email)
        }
    }

    private fun removeHeadLine(): (MutableList<String>) -> Unit =
        { lines -> if (lines.size > 0) lines.removeAt(0) }
}
