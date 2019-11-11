package it.xpug.kata.birthday_greetings

import java.io.File

class FileEmployees(val path: String) : Employees {
    override fun all(): List<Employee> {
        val line = File(path).readLines().iterator()

        skipHeader(line)

        val employees = mutableListOf<Employee>()
        line.forEachRemaining {
            employees.add(parse(it))
        }
        return employees.toList()
    }

    private fun parse(line: String): Employee {
        val employeeData = line.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
    }

    private fun skipHeader(line: Iterator<String>) {
        line.next()
    }
}