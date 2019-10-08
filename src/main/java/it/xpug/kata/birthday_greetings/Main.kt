package it.xpug.kata.birthday_greetings

fun main() {
    val service = BirthdayService()
    service.sendGreetings("employee_data.txt", XDate(), "localhost", 25)
}