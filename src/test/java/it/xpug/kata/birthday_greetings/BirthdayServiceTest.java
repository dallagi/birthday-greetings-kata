package it.xpug.kata.birthday_greetings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayServiceTest {

    @Mock
    private Sender sender;

    @Test
    public void should_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService();

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"), sender);

        verify(sender).sendGreetingsTo(any(Employee.class));
    }

    @Test
    public void should_not_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService();

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"), sender);

        verify(sender, never()).sendGreetingsTo(any(Employee.class));
    }

    @Test
    public void should_not_send_message_without_employees() throws Exception {
        BirthdayService birthdayService = new TestableBirthdayService();

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"), sender);

        verify(sender, never()).sendGreetingsTo(any(Employee.class));
    }

    private class TestableBirthdayService extends BirthdayService {
        @Override
        protected List<Employee> getEmployees(String fileName) throws IOException, ParseException {
            return new ArrayList<Employee>();
        }
    }
}