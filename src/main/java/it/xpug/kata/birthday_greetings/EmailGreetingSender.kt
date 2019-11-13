package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailGreetingSender(private val smtpHost: String, private val smtpPort: Int, private val sender: String):
    GreetingSender {
    override fun send(greeting: Greeting) {
        val message = createMimeMessage(greeting)
        Transport.send(message)
    }

    private fun createMimeMessage(greeting: Greeting): MimeMessage {
        val mimeMessage = MimeMessage(session())
        mimeMessage.setFrom(InternetAddress(sender))
        mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO, recipientEmail(greeting))
        mimeMessage.subject = greeting.subject()
        mimeMessage.setText(greeting.body())
        return mimeMessage
    }

    private fun recipientEmail(greeting: Greeting) =
        InternetAddress(greeting.recipient.email)

    private fun session(): Session? {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        return Session.getInstance(props, null)
    }

}
