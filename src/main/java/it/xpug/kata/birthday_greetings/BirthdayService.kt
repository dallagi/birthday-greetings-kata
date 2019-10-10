package it.xpug.kata.birthday_greetings

import java.io.File

class BirthdayService {

    fun sendGreetings(fileName: String, xDate: XDate, mailSender: Sender) {
        val lines = readFrom(fileName)

        lines.forEach { str ->
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
                val subject = "Happy Birthday!"
                mailSender.send("sender@here.com", subject, body, recipient)
            }
        }
    }

    private fun readFrom(fileName: String): MutableList<String> {
        val lines = File(fileName).readLines().toMutableList()
        lines.removeAt(0)
        return lines
    }
}

