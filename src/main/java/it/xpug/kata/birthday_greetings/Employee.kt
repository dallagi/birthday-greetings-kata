package it.xpug.kata.birthday_greetings

class Employee(val firstName: String, private val lastName: String, birthDate: String, val email: String) {

    private val parsedBirthDate = XDate(birthDate)

    fun isBirthday(today: XDate): Boolean {
        return today.isSameDay(parsedBirthDate)
    }

    override fun toString(): String {
        return "Employee $firstName $lastName <$email> born $parsedBirthDate"
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + parsedBirthDate.hashCode()
        result = prime * result + email.hashCode()
        result = prime * result + firstName.hashCode()
        result = prime * result + lastName.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (other !is Employee)
            return false
        val another = other as Employee?

        if (parsedBirthDate != another!!.parsedBirthDate)
            return false

        if (email != another.email)
            return false

        if (firstName != another.firstName)
            return false

        if (lastName != another.lastName)
            return false

        return true
    }
}
