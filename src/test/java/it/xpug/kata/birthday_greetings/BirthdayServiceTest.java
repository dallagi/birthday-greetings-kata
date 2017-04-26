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

    @Test
    public void should_not_send_message() throws Exception {
        TestableBirthdayService birthdayService = new TestableBirthdayService();

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"), "localhost", NONSTANDARD_PORT);

        assertFalse(birthdayService.messageSent);
    }

    private class TestableBirthdayService extends BirthdayService {
        public boolean messageSent = false;

        @Override
        protected void sendMessage(Employee employee, MailSender mailSender) throws AddressException, MessagingException {
            messageSent = true;
        }
    }
}