package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Created by alessiocoser on 26/04/2017.
 */
public interface Sender {
    void sendGreetingsTo(Employee employee) throws AddressException, MessagingException;
}
