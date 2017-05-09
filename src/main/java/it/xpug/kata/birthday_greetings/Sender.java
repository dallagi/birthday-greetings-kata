package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface Sender {
    void sendGreetingsTo(Employee employee) throws AddressException, MessagingException;
}
