package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class MailSenderTest {
    private lateinit var mailServer: SimpleSmtpServer
    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(2525)
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    fun `sends an email`() {
        val sender = MailSender("localhost", 2525)

        sender.send("sender@sender.local", "subject", "body", "recipient@recipient.local")

        assertThat(mailServer.receivedEmailSize).isEqualTo(1)
        val message = mailServer.receivedEmail.next() as SmtpMessage
        assertThat(message.body).isEqualTo("body")
        assertThat(message.getHeaderValue("Subject")).isEqualTo("subject")
        val recipients = message.getHeaderValues("To")
        assertThat(recipients).hasSize(1)
        assertThat(recipients[0]).isEqualTo("recipient@recipient.local")
    }
}