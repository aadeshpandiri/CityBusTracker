package com.example.CityBus

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

public class DateTimeUtils {
    var TIME_ONLY = "hh:mm a"
    var DAY_TIME_VIEW = "hh:mm"

    fun getDateString(date: Date?, format: String?): String { //Nov 15, 2019
        val sdf = SimpleDateFormat(format)
        return if (date != null) sdf.format(date) else ""
    }

    fun convertDate(date: Date?, formatTo: String?): String {
        return getDateString(date, formatTo)
    }

    fun getTimeOnly(): String {
        return convertDate(Calendar.getInstance().time, TIME_ONLY)
    }


}
