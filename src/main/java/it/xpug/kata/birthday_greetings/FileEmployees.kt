package it.xpug.kata.birthday_greetings

import java.io.File

class FileEmployees(private val path: String) : Employees {
    override fun withBirthdayOn(date: XDate): List<Employee> {
        val lines = File(path).readLines()

        return stripHeader(lines)
            .map { line -> employeeFrom(line) }
            .filter { employee -> employee.isBirthday(date) }

    }

    private fun employeeFrom(line: String): Employee {
        val data = line.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return Employee(data[1], data[0], data[2], data[3])
    }

    private fun stripHeader(lines: List<String>) = lines.drop(1)
}