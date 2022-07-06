package ru.synergy.data.repositories.books

import kotlinx.coroutines.flow.Flow
import ru.synergy.domain.entities.Volume

interface BooksLocalDataSource {// создаем закоадки книг котор понравились
    suspend fun bookmark(book: Volume)//поставить выделение
    suspend fun unbookmark(book: Volume)// снять выделение с книги
    suspend fun getBookmarks(): Flow<List<Volume>>//возращаем флщу тз букмарковс
}