package it.xpug.kata.birthday_greetings

import java.io.IOException
import java.text.ParseException
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService(private val employees: Employees) {

    @Throws(IOException::class, ParseException::class, AddressException::class, MessagingException::class)
    fun sendGreetings(xDate: XDate, smtpHost: String, smtpPort: Int) {
        employees.withBirthdayOn(xDate).forEach { employee ->
            val recipient = employee.email
            val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
            val subject = "Happy Birthday!"
            sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
        }
    }

    @Throws(AddressException::class, MessagingException::class)
    private fun sendMessage(
        smtpHost: String,
        smtpPort: Int,
        sender: String,
        subject: String,
        body: String,
        recipient: String
    ) {
        // Create a mail session
        val props = java.util.Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session = Session.getInstance(props, null)

        // Construct the message
        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress(sender))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
        msg.subject = subject
        msg.setText(body)

        // Send the message
        Transport.send(msg)
    }
}
