package it.xpug.kata.birthday_greetings

interface Employees {
    fun withBirthdayOn(date: XDate): List<Employee>
}