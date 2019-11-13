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
        val columns = split(line)
        return Employee(columns[1], columns[0], columns[2], columns[3])
    }

    private fun split(line: String) = line.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    private fun stripHeader(lines: List<String>) = lines.drop(1)
}