package it.xpug.kata.birthday_greetings

interface Sender {

    fun send(sender: String, subject: String, body: String, recipient: String)

}
