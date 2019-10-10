package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailSender(private val smtpHost: String, private val smtpPort: Int) : Sender {
    override fun send(sender: String, subject: String, body: String, recipient: String) {
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