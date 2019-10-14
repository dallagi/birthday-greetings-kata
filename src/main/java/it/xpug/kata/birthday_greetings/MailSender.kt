package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailSender(private val smtpHost: String, private val smtpPort: Int) : Sender {
    override fun send(sender: String, subject: String, body: String, recipient: String) {
        val session = Session.getInstance(properties(), null)

        Transport.send(MimeMessage(session).buildWith(sender, recipient, subject, body))
    }

    private fun properties(): Properties {
        return Properties().also {
            it["mail.smtp.host"] = smtpHost
            it["mail.smtp.port"] = "" + smtpPort
        }
    }
}
