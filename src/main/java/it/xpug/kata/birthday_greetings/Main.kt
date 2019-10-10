package it.xpug.kata.birthday_greetings

fun main() {
    val service = BirthdayService()
    val mailSender = MailSender("localhost", 25)
    val employeeRepository = FileEmployeeRepository("employee_data.txt")

    service.sendGreetings(XDate(), mailSender, employeeRepository)
}