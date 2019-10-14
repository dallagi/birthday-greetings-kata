package it.xpug.kata.birthday_greetings

fun main() {
    val mailSender = MailSender("localhost", 25)
    val employeeRepository = FileEmployeeRepository("employee_data.txt")

    BirthdayService(mailSender, employeeRepository).sendGreetings(XDate())
}