package it.xpug.kata.birthday_greetings

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class XDate {

    private var date: Date? = null

    val day: Int
        get() = getPartOfDate(GregorianCalendar.DAY_OF_MONTH)

    val month: Int
        get() = 1 + getPartOfDate(GregorianCalendar.MONTH)

    constructor() {
        date = Date()
    }

    @Throws(ParseException::class)
    constructor(yyyyMMdd: String) {
        date = SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd)
    }

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == this.day && anotherDate.month == this.month
    }

    override fun hashCode(): Int {
        return date!!.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is XDate)
            return false

        return (other as XDate?)!!.date == this.date
    }

    private fun getPartOfDate(part: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = date!!
        return calendar.get(part)
    }
}
