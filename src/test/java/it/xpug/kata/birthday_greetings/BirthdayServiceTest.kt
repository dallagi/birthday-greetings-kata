package it.xpug.kata.birthday_greetings

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class BirthdayServiceTest {
    private val NOT_TODAY_STRING = "2018/08/08"
    private val TODAY_STRING = "2019/09/09"
    private val TODAY = XDate(TODAY_STRING)
    private val sender : Sender = mock()
    private val repository : EmployeeRepository = mock()

    @Test
    fun `without employees do not send greetings`() {
        whenever(repository.all()).thenReturn(emptyList())

        BirthdayService(sender, repository).sendGreetings(TODAY)

        verify(sender, never()).send(any(), any(), any(), any())
    }

    @Test
    fun `without employees born today do not send greetings`() {
        whenever(repository.all()).thenReturn(listOf(bornAnotherDayEmployee("another@day.local")))

        BirthdayService(sender, repository).sendGreetings(TODAY)

        verify(sender, never()).send(any(), any(), any(), any())
    }

    @Test
    fun `with one employee born today send greetings once`() {
        whenever(repository.all()).thenReturn(listOf(bornTodayEmployee("Marco", "today@day.local")))

        BirthdayService(sender, repository).sendGreetings(TODAY)

        verify(sender).send(
            "sender@here.com",
            "Happy Birthday!",
            "Happy Birthday, dear Marco",
            "today@day.local"
        )
    }

    @Test
    fun `with two employee, one born today send greetings once`() {
        whenever(repository.all()).thenReturn(listOf(
            bornAnotherDayEmployee("another@day.local"),
            bornTodayEmployee("Marco", "today@day.local")
        ))

        BirthdayService(sender, repository).sendGreetings(TODAY)

        verify(sender).send(any(), any(), any(), eq("today@day.local"))
    }

    @Test
    fun `with three employee, two born today send greetings twice`() {
        whenever(repository.all()).thenReturn(listOf(
            bornAnotherDayEmployee("a-normal@day.local"),
            bornTodayEmployee("Marco", "today1@day.local"),
            bornTodayEmployee("Sonia", "today2@day.local")
        ))

        BirthdayService(sender, repository).sendGreetings(TODAY)

        verify(sender).send(any(), any(), any(), eq("today1@day.local"))
        verify(sender).send(any(), any(), any(), eq("today2@day.local"))
    }

    private fun bornAnotherDayEmployee(email: String) = Employee("name", "surname", NOT_TODAY_STRING, email)

    private fun bornTodayEmployee(name: String, email: String) = Employee(name, "surname", TODAY_STRING, email)
}
