package it.xpug.kata.birthday_greetings;

import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import static org.junit.Assert.*;

public class BirthdayServiceTest {

    private static final int NONSTANDARD_PORT = 9999;

    @Test
    public void should_send_message() throws Exception {
        TestableBirthdayService birthdayService = new TestableBirthdayService();

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"), "localhost", NONSTANDARD_PORT);

        assertTrue(birthdayService.messageSent);
    }

    private class TestableBirthdayService extends BirthdayService {
        public boolean messageSent = false;

        @Override
        protected void sendMessage(String smtpHost, int smtpPort, String sender, String subject, String body, String recipient) throws AddressException, MessagingException {
            messageSent = true;
        }
    }
}