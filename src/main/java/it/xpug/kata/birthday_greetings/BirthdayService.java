package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
		Sender sender = new MailSender(smtpHost, smtpPort);
		sendGreetings(fileName, xDate, sender);
	}

	public void sendGreetings(String fileName, XDate xDate, Sender sender) throws IOException, ParseException, MessagingException {
		for (Employee employee: getEmployees(fileName) ) {
			if (employee.isBirthday(xDate)) {
				sender.sendGreetingsTo(employee);
			}
		}
	}

	private List<Employee> getEmployees(String fileName) throws IOException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = in.readLine(); // skip header

		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
			employees.add(employee);
		}
		return employees;
	}

}
