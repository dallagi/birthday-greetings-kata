package it.xpug.kata.birthday_greetings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayServiceTest {

    @Mock
    private Sender sender;

    @Mock
    private EmployeesRepository employeesRepository;

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
        BirthdayService birthdayService = new BirthdayService();
        when(employeesRepository.all()).thenReturn(Collections.<Employee>emptyList());

        birthdayService.sendGreetings(new XDate("2008/10/08"), employeesRepository, sender);

        verify(sender, never()).sendGreetingsTo(any(Employee.class));
    }
}