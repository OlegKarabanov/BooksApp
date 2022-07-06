package ru.synergy.data.repositories.books

import kotlinx.coroutines.flow.Flow
import ru.synergy.data.repositories.books.BooksLocalDataSource
import ru.synergy.data.repositories.books.BooksRemoteDataSource
import ru.synergy.domain.common.Result
import ru.synergy.domain.entities.Volume
import ru.synergy.domain.repositories.BooksRepository

class BooksRepositoryImpl(
//реализуем имплементацию репозитория

//здесь объединяем два интерфейса
    private val localDataSource: BooksLocalDataSource,
    private val remoteDataSource: BooksRemoteDataSource
    //получаем книги по автору
) : BooksRepository {

    override suspend fun getRemoteBooks(author: String): Result<List<Volume>> {
        return remoteDataSource.getBooks(author)
    }

    override suspend fun getBookmarks(): Flow<List<Volume>> {
        return localDataSource.getBookmarks()
    }

    override suspend fun bookmark(book: Volume) {
        localDataSource.bookmark(book)//закладки по книге
    }

    override suspend fun unbookmark(book: Volume) {
        localDataSource.unbookmark(book) //удал. закладки
    }
}