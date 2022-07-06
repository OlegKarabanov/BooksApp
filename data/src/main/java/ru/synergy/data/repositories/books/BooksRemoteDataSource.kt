package ru.synergy.data.repositories.books

import ru.synergy.domain.entities.Volume
import ru.synergy.domain.common.Result

interface BooksRemoteDataSource {
    suspend fun getBooks(author: String): Result<List<Volume>>
    //ппередаем автора в виде стринги и возвращаем  лист волюмов
}