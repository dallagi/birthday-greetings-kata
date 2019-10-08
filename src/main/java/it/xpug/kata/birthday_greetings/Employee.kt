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
        result = prime * result + (parsedBirthDate.hashCode() ?: 0)
        result = prime * result + (email.hashCode() ?: 0)
        result = prime * result + (firstName.hashCode() ?: 0)
        result = prime * result + (lastName.hashCode() ?: 0)
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj)
            return true
        if (obj == null)
            return false
        if (obj !is Employee)
            return false
        val other = obj as Employee?
        if (parsedBirthDate == null) {
            if (other!!.parsedBirthDate != null)
                return false
        } else if (parsedBirthDate != other!!.parsedBirthDate)
            return false
        if (email == null) {
            if (other.email != null)
                return false
        } else if (email != other.email)
            return false
        if (firstName == null) {
            if (other.firstName != null)
                return false
        } else if (firstName != other.firstName)
            return false
        if (lastName == null) {
            if (other.lastName != null)
                return false
        } else if (lastName != other.lastName)
            return false
        return true
    }
}
