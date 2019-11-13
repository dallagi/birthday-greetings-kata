package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EmailGreetingSenderShould {
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
    fun send_greeting_as_email() {
        val greeting = Greeting(Employee("Mario", "Rossi", "1990/01/01", "mario@rossi.it"))
        EmailGreetingSender("localhost", NONSTANDARD_PORT, "sender@example.com").send(greeting)

        assertEquals(1, mailServer.receivedEmailSize.toLong())
        val receivedMessage = mailServer.receivedEmail.next() as SmtpMessage
        assertEquals(greeting.body(), receivedMessage.body)
        assertEquals(greeting.subject(), subject(receivedMessage))
        val recipients = recipients(receivedMessage)
        assertEquals(1, recipients.size.toLong())
        assertEquals("mario@rossi.it", recipients[0].toString())

    }

    private fun recipients(message: SmtpMessage) = message.getHeaderValues("To")

    private fun subject(message: SmtpMessage) = message.getHeaderValue("Subject")

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}