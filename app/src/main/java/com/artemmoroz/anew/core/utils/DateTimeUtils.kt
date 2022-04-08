package com.artemmoroz.anew.core.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val NEWS_API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    fun getRelativeDate(date: String): String {
        val sf = SimpleDateFormat(NEWS_API_FORMAT, Locale.getDefault())
        val dateMillis: Long = sf.parse(date).time
        return DateUtils.getRelativeTimeSpanString(
            dateMillis,
            System.currentTimeMillis(),
            DateUtils.SECOND_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_RELATIVE
        ).toString()
    }
}