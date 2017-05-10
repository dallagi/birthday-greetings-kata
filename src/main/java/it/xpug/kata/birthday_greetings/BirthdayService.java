package it.xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

	private EmployeesRepository employeesRepository;
    private Sender sender;

    public BirthdayService(EmployeesRepository employeesRepository, Sender sender) {
        this.employeesRepository = employeesRepository;
        this.sender = sender;
    }

    public void sendGreetings(XDate xDate) throws IOException, ParseException, MessagingException {
		for (Employee employee: employeesRepository.all()) {
			if (employee.isBirthday(xDate)) {
				sender.sendGreetingsTo(employee);
			}
		}
	}
}
