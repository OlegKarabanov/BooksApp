package ru.synergy.booksapp.entities

import ru.synergy.booksapp.entities.BookmarkStatus

data class BookWithStatus(//импортируем букмаркстатус
    val id: String,
    val title: String,
    val authors: List<String>,
    val imageUrl: String?,
    val status: BookmarkStatus
)