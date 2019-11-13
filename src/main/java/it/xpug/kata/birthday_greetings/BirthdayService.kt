package it.xpug.kata.birthday_greetings

import java.io.IOException
import java.text.ParseException
import javax.mail.MessagingException
import javax.mail.internet.AddressException

class BirthdayService(private val greetingSender: GreetingSender, private val employees: Employees) {

    @Throws(IOException::class, ParseException::class, AddressException::class, MessagingException::class)
    fun sendGreetings(date: XDate) {
        employees.withBirthdayOn(date).forEach { employee ->
            greetingSender.send(Greeting(employee))
        }
    }
}
