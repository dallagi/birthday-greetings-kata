package it.xpug.kata.birthday_greetings

class BirthdayService {

    fun sendGreetings(xDate: XDate, mailSender: Sender, fileEmployeeRepository: EmployeeRepository) {
        fileEmployeeRepository.all().forEach { employee ->
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
                val subject = "Happy Birthday!"
                mailSender.send("sender@here.com", subject, body, recipient)
            }
        }
    }
}

