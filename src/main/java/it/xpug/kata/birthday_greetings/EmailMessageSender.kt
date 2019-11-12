package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailMessageSender(private val smtpHost: String, private val smtpPort: Int, private val sender: String):
    MessageSender {
    override fun send(message: Message) {
        val mimeMessage = createMimeMessage(message)
        Transport.send(mimeMessage)
    }

    private fun createMimeMessage(message: Message): MimeMessage {
        val mimeMessage = MimeMessage(session())
        mimeMessage.setFrom(InternetAddress(sender))
        mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO, InternetAddress(message.recipient.email))
        mimeMessage.subject = message.subject
        mimeMessage.setText(message.body)
        return mimeMessage
    }

    private fun session(): Session? {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session = Session.getInstance(props, null)
        return session
    }

}
