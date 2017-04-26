package it.xpug.kata.birthday_greetings;

import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BirthdayServiceTest {

    private static final int NONSTANDARD_PORT = 9999;

    @Test
    public void should_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService();

        SpyMailSender mailSender = new SpyMailSender();
        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"), mailSender);

        assertTrue(mailSender.greetingsSent);
    }

    @Test
    public void should_not_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService();

        SpyMailSender mailSender = new SpyMailSender();
        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"), mailSender);

        assertFalse(mailSender.greetingsSent);
    }

    private class SpyMailSender extends MailSender {
        public boolean greetingsSent = false;

        public SpyMailSender() {
            super(null, -1);
        }

        @Override
        public void sendGreetingsTo(Employee employee) throws AddressException, MessagingException {
            greetingsSent = true;
        }
    }
}