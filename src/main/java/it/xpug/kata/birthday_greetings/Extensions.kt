package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun MimeMessage.buildWith(sender: String, recipient: String, subject: String, body: String): MimeMessage {
    setFrom(InternetAddress(sender))
    setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
    this.subject = subject
    setText(body)

    return this
}
