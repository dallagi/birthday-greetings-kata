package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EmailMessageSenderShould {
    private lateinit var mailServer: SimpleSmtpServer

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(1000)
    }

    @Test
    fun send_message_as_email() {
        val messageToSend = Message("subject", "body", Employee("Mario", "Rossi", "1990/01/01", "mario@rossi.it"))
        EmailMessageSender("localhost", NONSTANDARD_PORT, "sender@example.com").send(messageToSend)

        assertEquals(1, mailServer.receivedEmailSize.toLong())
        val receivedMessage = mailServer.receivedEmail.next() as SmtpMessage
        assertEquals(messageToSend.body, receivedMessage.body)
        assertEquals(messageToSend.subject, receivedMessage.getHeaderValue("Subject"))
        val recipients = receivedMessage.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("mario@rossi.it", recipients[0].toString())

    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}