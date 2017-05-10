package it.xpug.kata.birthday_greetings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayServiceTest {

    public static final String BIRTHDAY_DATE = "2008/10/08";
    public static final String ANOTHER_DATE = "2008/01/01";

    @Mock
    private Sender sender;

    @Mock
    private EmployeesRepository employeesRepository;

    @Test
    public void should_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService(employeesRepository, sender);

        when(employeesRepository.all()).thenReturn(singletonList(anEmployWith(BIRTHDAY_DATE)));

        birthdayService.sendGreetings(new XDate(BIRTHDAY_DATE));

        verify(sender).sendGreetingsTo(any(Employee.class));
    }

    @Test
    public void should_not_send_message() throws Exception {
        BirthdayService birthdayService = new BirthdayService(employeesRepository, sender);

        when(employeesRepository.all()).thenReturn(singletonList(anEmployWith(BIRTHDAY_DATE)));

        birthdayService.sendGreetings(new XDate(ANOTHER_DATE));

        verify(sender, never()).sendGreetingsTo(any(Employee.class));
    }

    @Test
    public void should_not_send_message_without_employees() throws Exception {
        BirthdayService birthdayService = new BirthdayService(employeesRepository, sender);
        when(employeesRepository.all()).thenReturn(Collections.<Employee>emptyList());

        birthdayService.sendGreetings(new XDate("2008/10/08"));

        verify(sender, never()).sendGreetingsTo(any(Employee.class));
    }

    private Employee anEmployWith(String birthdayDate) throws ParseException {
        return new Employee(null, null, birthdayDate, null);
    }
}