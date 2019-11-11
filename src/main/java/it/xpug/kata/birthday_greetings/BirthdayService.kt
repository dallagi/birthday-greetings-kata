package it.xpug.kata.birthday_greetings

import java.io.File
import java.io.IOException
import java.text.ParseException
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService {

    @Throws(IOException::class, ParseException::class, AddressException::class, MessagingException::class)
    fun sendGreetings(fileName: String, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val line = File(fileName).readLines().iterator()

        line.next()

        while(line.hasNext()) {
            val str = line.next()
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
                val subject = "Happy Birthday!"
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
            }
        }
    }

    @Throws(AddressException::class, MessagingException::class)
    private fun sendMessage(smtpHost: String, smtpPort: Int, sender: String, subject: String, body: String, recipient: String) {
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
