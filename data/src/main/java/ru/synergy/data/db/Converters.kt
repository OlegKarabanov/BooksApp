package ru.synergy.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromAuthorsList(value: List<String>): String {// получаем лист стрингов
        return value.joinToString { "," }//на выходе джойним стринги и выдаем одну большую стрингу
    }

    @TypeConverter
    fun toAuthorsList(value: String): List<String> {// по запятым делаем отдельных авторов
        return value.split(",")
    }
}