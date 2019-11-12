package it.xpug.kata.birthday_greetings

import java.io.IOException
import java.text.ParseException
import javax.mail.MessagingException
import javax.mail.internet.AddressException

class BirthdayService(private val messageSender: MessageSender, private val employees: Employees) {

    @Throws(IOException::class, ParseException::class, AddressException::class, MessagingException::class)
    fun sendGreetings(xDate: XDate) {
        employees.withBirthdayOn(xDate).forEach { employee ->
            messageSender.send(happyBirthdayMessageFor(employee))
        }
    }

    private fun happyBirthdayMessageFor(employee: Employee): Message {
        val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
        val subject = "Happy Birthday!"

        return Message(subject, body, employee)
    }
}
