package it.xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

	private EmployeesRepository employeesRepository;

    public BirthdayService() {
    }

    public BirthdayService(EmployeesRepository employeesRepository) {

        this.employeesRepository = employeesRepository;
    }

	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
		Sender sender = new MailSender(smtpHost, smtpPort);
        this.employeesRepository = new FileEmployeesRepository(fileName);
        sendGreetings(xDate, sender);
    }

    public void sendGreetings(XDate xDate, Sender sender) throws IOException, ParseException, MessagingException {
		for (Employee employee: employeesRepository.all()) {
			if (employee.isBirthday(xDate)) {
				sender.sendGreetingsTo(employee);
			}
		}
	}
}
