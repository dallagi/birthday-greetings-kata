package it.xpug.kata.birthday_greetings

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.FileNotFoundException

class FileEmployeeRepositoryTest {
    @Test
    fun `returns all employees stored in a file`() {
        val repository = FileEmployeeRepository("employee_data.txt")

        assertThat(repository.all()).hasSize(2)
    }

    @Test
    fun `returns a list of employee`() {
        val repository = FileEmployeeRepository("employee_data.txt")

        assertThat(repository.all().first()).isEqualTo(Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com"))
    }

    @Test(expected = FileNotFoundException::class)
    fun `throws a FileNotFoundException when file is not present`() {
        val repository = FileEmployeeRepository("not_existing.txt")

        repository.all()
    }

    @Test
    fun `returns an empty list if file is empty`() {
        val repository = FileEmployeeRepository("empty.txt")

        assertThat(repository.all()).hasSize(0)
    }
}