package it.xpug.kata.birthday_greetings

class BirthdayService(private val mailSender: Sender, private val fileEmployeeRepository: EmployeeRepository) {

    private val sender = "sender@here.com"
    private val subject = "Happy Birthday!"

    fun sendGreetings(xDate: XDate) {
        fileEmployeeRepository.all().forEach { employee ->
            if (employee.isBirthday(xDate)) {
                mailSender.send(sender, subject, body(employee), employee.email)
            }
        }
    }

    private fun body(employee: Employee) =
        "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName)
}

