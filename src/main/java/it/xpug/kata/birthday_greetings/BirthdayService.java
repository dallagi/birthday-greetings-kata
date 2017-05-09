package it.xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
		Sender sender = new MailSender(smtpHost, smtpPort);
		sendGreetings(fileName, xDate, sender);
	}

	public void sendGreetings(String fileName, XDate xDate, Sender sender) throws IOException, ParseException, MessagingException {
		for (Employee employee: getEmployees(fileName)) {
			if (employee.isBirthday(xDate)) {
				sender.sendGreetingsTo(employee);
			}
		}
	}

	protected List<Employee> getEmployees(String fileName) throws IOException, ParseException {
		return new FileEmployeesRepository(fileName).all();
	}

}
