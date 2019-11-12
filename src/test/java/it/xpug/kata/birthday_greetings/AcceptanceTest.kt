package it.xpug.kata.birthday_greetings

import org.junit.Assert.*

import org.junit.*

import com.dumbster.smtp.*


class AcceptanceTest {
    private lateinit var birthdayService: BirthdayService
    private lateinit var mailServer: SimpleSmtpServer

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
        birthdayService = BirthdayService(
            EmailMessageSender("localhost", NONSTANDARD_PORT, "from@xxx.com"),
            FileEmployees("employee_data.txt")
        )
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(1000)
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/10/08"))

        assertEquals("message not sent?", 1, mailServer.receivedEmailSize.toLong())
        val message = mailServer.receivedEmail.next() as SmtpMessage
        assertEquals("Happy Birthday, dear John", message.body)
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))
        val recipients = message.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"))

        assertEquals("what? messages?", 0, mailServer.receivedEmailSize.toLong())
    }

    companion object {
        private val NONSTANDARD_PORT = 9999
    }
}
