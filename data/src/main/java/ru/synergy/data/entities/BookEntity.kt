package ru.synergy.data.entities

import androidx.room.Entity // написать обязательно это объявление сущности
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey//конструктор
    val id: String, //
    val title: String,//
    val authors: List<String>,//лист из стрингов
    val imageUrl: String?//может быть или не быть обложки
)