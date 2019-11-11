package it.xpug.kata.birthday_greetings

fun main() {
    val service = BirthdayService(FileEmployees("employee_data.txt"))
    service.sendGreetings(XDate(), "localhost", 25)
}