package it.xpug.kata.birthday_greetings

data class Employee(val firstName: String, private val lastName: String, val birthDate: String, val email: String) {

    private val parsedBirthDate = XDate(birthDate)

    fun isBirthday(today: XDate): Boolean {
        return today.isSameDay(parsedBirthDate)
    }

    override fun toString(): String {
        return "Employee $firstName $lastName <$email> born $parsedBirthDate"
    }
}
