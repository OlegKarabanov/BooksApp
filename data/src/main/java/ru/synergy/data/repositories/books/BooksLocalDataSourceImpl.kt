package ru.synergy.data.repositories.books

import ru.synergy.data.db.BookDao
import ru.synergy.data.mappers.BookEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.synergy.data.repositories.books.BooksLocalDataSource
import ru.synergy.domain.entities.Volume

class BooksLocalDataSourceImpl(//конструктор
    private val bookDao: BookDao, // объявляем переменные
    private val dispatcher: CoroutineDispatcher,//-
    private val bookEntityMapper: BookEntityMapper//-
) : BooksLocalDataSource {//на выходе имплементр методы буксдатасурс
//имплементируем все методы
    override suspend fun bookmark(book: Volume) = withContext(dispatcher) {
        bookDao.saveBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unbookmark(book: Volume) = withContext(dispatcher) {
        bookDao.deleteBook(bookEntityMapper.toBookEntity(book)) // делитием нект книгу, преобразов ее
    }

    override suspend fun getBookmarks(): Flow<List<Volume>> {
        val savedBooksFlow = bookDao.getSavedBooks()// созд сейвдбуксфлоу и сохр ее
        return savedBooksFlow.map { list -> // лямбда
            list.map { element ->
                bookEntityMapper.toVolume(element)//каждый эемент преобразо через маппер
                //мапа пробеж по всем элементам и положит в новый массив
            }
        }
    }
}