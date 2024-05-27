package com.ferrarib.todolist.data.local

import androidx.room.TypeConverter
import java.util.Date

class DateTimeTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.run { Date(this) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}