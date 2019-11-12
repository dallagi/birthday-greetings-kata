package it.xpug.kata.birthday_greetings

fun main() {
    val mailSender = EmailMessageSender("localhost", 25, "sender@here.com")
    val service = BirthdayService(mailSender, FileEmployees("employee_data.txt"))
    service.sendGreetings(XDate())
}