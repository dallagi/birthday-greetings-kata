package it.xpug.kata.birthday_greetings

class Greeting(val recipient: Employee) {
    fun subject() = "Happy Birthday!"

    fun body() = "Happy Birthday, dear %NAME%".replace("%NAME%", recipient.firstName)
}
