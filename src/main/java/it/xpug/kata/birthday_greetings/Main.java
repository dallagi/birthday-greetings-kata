package it.xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, MessagingException {
        EmployeesRepository employeesRepository = new FileEmployeesRepository("employee_data.txt");
        Sender mailSender = new MailSender("localhost", 25);

        BirthdayService service = new BirthdayService(employeesRepository, mailSender);

		service.sendGreetings(new XDate());
	}
}
